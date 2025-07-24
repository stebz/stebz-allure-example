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
package org.stebz.example.allure.selenide.page;

import org.stebz.annotation.Step;

import java.net.URL;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public final class SinglePage {

  public SinglePage() {
  }

  @Step
  public SinglePage open_page() {
    open(getResource("auth_page.html"));
    return this;
  }

  @Step
  public SinglePage should_have_visible_username_field() {
    $("#username").shouldBe(visible);
    return this;
  }

  @Step
  public SinglePage type_username(String username) {
    $("#username").append(username);
    return this;
  }

  @Step
  public SinglePage should_have_visible_password_field() {
    $("#password").shouldBe(visible);
    return this;
  }

  @Step
  public SinglePage type_password(String username) {
    $("#password").append(username);
    return this;
  }

  @Step
  public SinglePage should_have_visible_login_button() {
    $("#login-button").shouldBe(visible);
    return this;
  }

  @Step
  public SinglePage click_on_login_button() {
    $("#login-button").click();
    return this;
  }

  @Step
  public SinglePage should_have_successful_login_message() {
    $("#message").shouldBe(visible, exactText("You are logged in"));
    return this;
  }

  private static String getResource(String fileName) {
    URL resource = SinglePage.class.getClassLoader().getResource(fileName);
    return resource == null ? "" : resource.toString();
  }
}
