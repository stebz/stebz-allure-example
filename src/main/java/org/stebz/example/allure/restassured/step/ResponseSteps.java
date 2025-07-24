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

import io.restassured.response.Response;
import net.javacrumbs.jsonunit.core.Option;
import org.stebz.annotation.WithName;
import org.stebz.annotation.WithParams;
import org.stebz.step.executable.ConsumerStep;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

public final class ResponseSteps {

  private ResponseSteps() {
  }

  // @formatter:off

  public static ConsumerStep<Response> responseStatusCodeIsOK() {
    return responseStatusCodeIs(200);
  }

  @WithName("Response status code should be {statusCode}")
  @WithParams
  public static ConsumerStep<Response> responseStatusCodeIs(int statusCode) { return ConsumerStep.of(response ->
    response.then().statusCode(statusCode)
  ); }

  @WithName("Response body should be equal to expected")
  public static ConsumerStep<Response> responseBodyEqualsTo(String expected) { return ConsumerStep.of(response ->
    assertThatJson(response.body() == null ? "" : response.body().asString())
      .withOptions(Option.REPORTING_DIFFERENCE_AS_NORMALIZED_STRING)
      .withOptions(Option.IGNORING_ARRAY_ORDER)
      .isEqualTo(expected)
  ); }

  @WithName("Response body should contain expected")
  public static ConsumerStep<Response> responseBodyContains(String expected) { return ConsumerStep.of(response ->
    assertThatJson(response.body() == null ? "" : response.body().asString())
      .withOptions(Option.REPORTING_DIFFERENCE_AS_NORMALIZED_STRING)
      .withOptions(Option.IGNORING_ARRAY_ORDER)
      .withOptions(Option.IGNORING_EXTRA_ARRAY_ITEMS)
      .withOptions(Option.IGNORING_EXTRA_FIELDS)
      .isEqualTo(expected)
  ); }

  // @formatter:on
}
