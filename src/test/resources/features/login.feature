Feature: Login
Scenario Outline: Login
Given user navigates to page

When I enter Username as "<username>" and Password as "<password>"

Then login successful
Examples:
| username  | password  |
| thaodt0123@gmail.com | password |
| test@gmail.com | password |