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
package org.stebz.example.allure.restassured.step;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.stebz.annotation.WithName;
import org.stebz.annotation.WithRetry;
import org.stebz.step.executable.SupplierStep;

import java.net.SocketTimeoutException;

public final class RequestSteps {

  private RequestSteps() {
  }

  // @formatter:off

  @WithName("Send GET /pet/{id} request")
  @WithRetry(count = 2, on = SocketTimeoutException.class)
  public static SupplierStep<Response> sendGetPetRequest(int id) { return SupplierStep.of(() ->
    RestAssured.given()
      .accept(ContentType.JSON)
      .pathParam("id", id)
      .get("https://petstore3.swagger.io/api/v3/pet/{id}")
  ); }

  @WithName("Send POST /pet request")
  public static SupplierStep<Response> sendPostPetRequest(Object body) { return SupplierStep.of(() ->
    RestAssured.given()
      .accept(ContentType.JSON)
      .contentType(ContentType.JSON)
      .body(body)
      .post("https://petstore3.swagger.io/api/v3/pet")
  ); }

  // @formatter:on
}
