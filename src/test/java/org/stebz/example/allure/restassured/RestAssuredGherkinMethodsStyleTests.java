/*
 * MIT License
 *
 * Copyright (c) 2025 Evgenii Plugatar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.stebz.example.allure.restassured;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.stebz.StebzGherkinMethods.*;
import static org.stebz.example.allure.restassured.step.RequestSteps.sendGetPetRequest;
import static org.stebz.example.allure.restassured.step.RequestSteps.sendPostPetRequest;
import static org.stebz.example.allure.restassured.step.ResponseSteps.*;

class RestAssuredGherkinMethodsStyleTests {

  @BeforeAll
  static void beforeAll() {
    if (RestAssured.filters().isEmpty()) {
      RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured());
    }
  }

  @Test
  void getPetByNonExistingId() {
    Response response =
      When(sendGetPetRequest(-1).withNewName(name -> name + " with negative id"));
    Then(responseStatusCodeIs(404), response);
    And(responseBodyEqualsTo("""
      {
          "code": 1,
          "type": "error",
          "message": "Pet not found"
      }
      """), response);
  }

  @Test
  void getPetByExistingId() {
    Response response =
      When(sendGetPetRequest(1).withNewName(name -> name + " with existing id"));
    Then(responseStatusCodeIsOK(), response);
    And(responseBodyContains("""
      {
        "id": 1,
        "status": "available"
      }
      """), response);
  }

  @Test
  void createPetAndGet() {
    Response postResponse
      = When(sendPostPetRequest("""
      {
        "id": 10,
        "name": "doggie",
        "photoUrls": [
          "string"
        ],
        "status": "available"
      }
      """));
    Then(responseStatusCodeIsOK(), postResponse);
    And("Response body contains given id, name, photoUrls, status", responseBodyContains("""
        {
          "id": 10,
          "name": "doggie",
          "photoUrls": [
            "string"
          ],
          "status": "available"
        }
      """), postResponse);

    Response getResponse =
      When(sendGetPetRequest(10));
    And(responseStatusCodeIsOK(), getResponse);
    Then("Response body contains id and status", responseBodyContains("""
      {
        "id": 10,
        "status": "available"
      }
      """), getResponse);
  }
}