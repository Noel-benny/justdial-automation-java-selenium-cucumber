# 🚗 JustDial Automation Framework

This project is a comprehensive automation framework built using **Java**, **Selenium WebDriver**, and **Cucumber (BDD)** to simulate and validate a user journey on the JustDial website. It demonstrates real-world automation scenarios such as service discovery, form validation, menu navigation, and multi-browser testing.

---

## 🎯 Problem Statement

**Objective**: Automate the identification and interaction with car wash services and other features on JustDial.

### Key Requirements:
1. **Identify Car Wash Services**:
   - Display 5 car wash services near the user's location.
   - Services must have:
     - Rating > 4
     - Customer votes > 20
   - Extract and display service name and phone number.

2. **Free Listing Form Validation**:
   - Attempt to register a business with an invalid mobile number.
   - Capture and display the error message.
   - Take a screenshot of the failed attempt.
   - Retry with a valid number and capture a success screenshot.

3. **Menu Navigation and Extraction**:
   - Navigate to Fitness > Gym.
   - Extract all sub-menu items.
   - Store and display them in a list.

---

## 🧪 Test Scenarios

### ✅ Scenario: Car Wash to Gym Navigation
- Launch browser (Chrome or Edge)
- Handle popups on JustDial homepage
- Search for car wash services
- Apply filters for rating and votes
- Print top-rated services with contact details
- Submit Free Listing form with invalid and valid mobile numbers
- Navigate to Gym section and extract sub-menu items
- Close browser

### 🧾 Scenario Outline (Cucumber)
```gherkin
Scenario Outline: Car Wash to Gym navigation on "<browser>"
  Given User launches browser "<browser>" and opens JustDial website
  When User handles car wash popups
  And User searches for car washing services
  And User applies rating filter
  And User prints top rated services
  Then User submits Free Listing with mobile number 1234567890 and retries with valid number
  And User returns to homepage
  And User navigates to Gym menu
  Then User prints gym submenu items
  And User closes browser

Examples:
  | browser |
  | edge    |
  | chrome  |
