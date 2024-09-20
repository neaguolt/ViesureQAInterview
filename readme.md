# QA-Tasks
QA Tasks assignment project 


## Weather 'application' overview

You are assigned to a team which is developing weather related application's **MVP** backend.
Your task is to create automated tests for the **API that the application will use**.

The MVP app will use one API, that tells the weather conditions for a fixed city. (Condition, description, temperatures ...etc.).

The API will return the following fields: `city`, `condition`, `icon`, `description` `conditionId` `tempInFahrenheit` and `tempInCelsius`


## Weather backend's acceptance criteria-s

* The **city field** would give a fixed city name as a string
* The **conditionID field** gives an ID of the current condition

  | ID   | Value   |
  | ---- | ------  |
  | 1    | clear   |
  | 2    | windy   |
  | 3    | mist    |
  | 4    | drizzle |
  | 5    | dust    |

* The **condition field** would tell the weather condition as a string based on condition id
    * Can have the following values `clear`, `windy`, `mist`, `drizzle` and `dust`
* The **icon field** gives a `png` type image (string) that correlates (same as) the current condition
* The **weather object** contains the temperature in both Fahrenheit and Celsius
    * The **tempInFahrenheit field** contains the current temperature in Fahrenheits
        * Should have 0 digits
    * The **tempInCelsius field** contains the current temperature in Celsius
        * Should have 0 digits
        * Calculated from the tempInFahrenheit field
        * Uses normal rounding rules
* The **description field** returns a description text of the current weather
    * The description is a fixed text with a suffix
    * The description suffix is calculated based on the temperature in celsius
    * Calculation rules:

| Rule            | Description   |
| -------------   | ------------- |
| celsius <= 0    | freezing      |
| celsius < 10    | cold          |
| celsius < 20    | mild          |
| celsius < 25    | warm          |
| celsius >= 25   | hot           |


### The API:

The base url of the API is: `https://backend-interview.tools.gcp.viesure.io/`
<br >**Eg.:** GET `https://backend-interview.tools.gcp.viesure.io/weather`

**GET .../weather**
* You can fetch the endpoint through  `{base_url}/weather` with the following data structure:
```curl
curl -X 'GET' \
  '{base_url}/weather' \
  -H 'accept: application/json'
```
```json
{
  "city"        : "Vienna",
  "condition"   : "clear",
  "icon"        : "clear.PNG",
  "conditionId" : 1,
  "description" : "The weather is mild",
  "weather": {
    "tempInFahrenheit" : 60,
    "tempInCelsius"    : 15
  }
}
```

### Helper API-s

You are also provided 2 helper API-s to be able to set the states for the GET weather API

Helper API-s don't need to be tested, they exist so you can set the states of the API under test

#### PUT .../weather/temp

You can set the Fahrenheit temperature with this API, by sending an Integer
* You can call the endpoint through  `{base_url}/weather/temp`
```cURL
curl -X 'PUT' \
  '{base_url}/weather/temp' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "tempInFahrenheit": 17
}'
```
#### PUT .../weather/condition

You can set the Condition ID with this API, by sending an Integer
* You can call the endpoint through  `{base_url}/weather/condition`
```cURL
curl -X 'PUT' \
  '{base_url}/weather/condition' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "condition": 1
}'
```


## Your task
You need to create a test automation framework and implement test cases for the weather API (defined above)
using **Cucumber,** **testng** and **Selenium** where needed
* Define Cucumber scenarios
    * In designing your cucumber scenarios, keep in mind that it should be understandable for someone without a technical expertise ( Eg.: Business representatives). Design them as you would have to demo it to business stakeholders
* Use the following language, frameworks and technologies within the automation project:
    * Java
    * Selenium
    * Maven and POM
    * BDD and Executed cucumber tests.
    * Add an external reporting framework of your choice. (eg.: Extent, Allure ...etc)
    * You can add any other helper library if you wish
* Create a README file with some informative description of your project
    * Also include the results of the test report of your scenarios (in the readme)
* Open a pull request to this project.

## Bonus

To also make use of selenium, test a few fields from `https://openweathermap.org/`

#### Task1
Verify the main page's search field contains correct placeholder text

![Search](resources/openweather_search_placeholder_spring.png)

#### Task2
* Search for `Sydney`, and select `Sydney, AU` from the list
* Verify the selected city's title is correct
* Verify that the date shown is correct
* Verify that the time shown is correct


![City](./resources/openweather_search_city.png)


## Next Step
* I ll try to applay POM (page object model) decouple the locater code from assertions code
  1. create the following  packages:
     - pages
     - features
     - stepsDefinitions
     - runner
     - utility
     
## Step - Create a cucumber file for Task 1
  * I am trying to create a feature in cucumber to implement a test case , the requirments are :
    Verify the main page's search field contains correct placeholder text
    My approch is:
       I create a placeholder.feature file with the content:
       feature: "Verify placeholder in the main page's search field"
       scenario: "Verify the main page's search field contains correct placeholder text"
       Given User open site main page
       When User looks at the main page's search field
       Then User should see correct placeholder text
       
  * is my approach correct? please just focus only on the cucumber file for the moment  

  *Yes, your approach is almost correct, but I will help fine-tune your Cucumber feature file to align more closely with Cucumber’s best practices.

Let's break down what your file does and how it can be improved:

### Key Concepts:
Feature:  
    This is the high-level description of what you are testing.  
    It should summarize what the feature (in this case, verifying the placeholder text) is doing.  
Scenario:   
    This defines a specific test case that describes a particular situation or flow in your application.  
Given, When, Then:    
    These keywords are part of Cucumber's "Gherkin" syntax, 
which you use to define steps in your scenario. Each keyword has a specific role:

* Given: Describes the starting point (the initial context).
* When: Describes the action being performed.
* Then: Describes the expected outcome or result.
* Now, let’s tweak your feature file:

### Revised placeholder.feature
gherkin code

Feature: Verify the placeholder text in the main page's search field

Scenario: The search field contains the correct placeholder text  
  Given the user is on the main page  
  When the user looks at the search field    
  Then the user should see the placeholder text "Search..."  

### Explanation of Improvements:
* Feature Description:  
I changed the feature description to something more concise:  
  "Verify the placeholder text in the main page's search field."  
This keeps it clear and focused on what you're testing.

* Scenario Name:
The scenario name was slightly reworded to make it more specific.  
Instead of just saying "Verify the main page's search field contains correct placeholder text,"  
I made it reflect what exactly is happening in the scenario:   
"The search field contains the correct placeholder text."

* Step Definitions:
 Given: I rephrased "User open site main page" to "the user is on the main page." This is a common convention and sounds more natural.
 When: "User looks at the main page's search field" is correct, but simplifying to "the user looks at the search field" is slightly cleaner.
 Then: Instead of saying "correct placeholder text," you should specify what the placeholder text actually is, like "Search...". This helps make the scenario more concrete and testable. Cucumber scenarios should be explicit in their expected outcomes.

* Summary:
Your overall structure is correct, but small changes in clarity and specificity make a big difference in writing good Cucumber tests. 
* The revised version is more readable and aligns better with Gherkin syntax best practices.

