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
package org.stebz.example.allure.selenide.step;

import org.stebz.annotation.Step;
import org.stebz.example.allure.selenide.page.SinglePage;
import org.stebz.step.executable.RunnableStep;

public final class PageSteps {

  private PageSteps() {
  }

  // @formatter:off

  @Step("user is authorized as {username} / {password}")
  public static RunnableStep user_is_authorized_as(String username,
                                                   String password) { return RunnableStep.of(() ->
    new SinglePage()
      .open_page()
      .should_have_visible_username_field()
      .should_have_visible_password_field()
      .should_have_visible_login_button()
      .type_username(username)
      .type_password(password)
      .click_on_login_button()
  ); }

  @Step
  public static RunnableStep user_should_be_authorized() { return RunnableStep.of(() ->
    new SinglePage()
      .should_have_successful_login_message()
  ); }

  // @formatter:on
}
