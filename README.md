# gumtree_android_challenge

## Setup

- Checkout
- Create `private.properties` file in the root folder of the project.
- Add these properties:

```
# openweathermap api key
api.key.development=YOUR_API_KEY
api.key.production=YOUR_API_KEY
```

- Run the project

## Run the project

You can simply can use the script run.sh

- $./run.sh

But if you need permissions, just run `chmod`

- $sudo chmod 755 ./run.sh
- $./run.sh

# ShowCase

![](documentation/video_weather_demo.gif)

# REPORTS

I've been using `Circle CI` and these are the reports I can show

## Success last execution

[Link here!](https://app.circleci.com/pipelines/github/jmarkstar/gumtree_android_challenge/26/workflows/d41a8bcb-1b23-45e1-b745-f0201c0065e1/jobs/28/steps)


![](documentation/last_execution.png)

Artifacts
![](documentation/last_execution_artifacts.png)

## Unit test: Junit 4
[Link here!](https://28-349586620-gh.circle-artifacts.com/0/reports/tests/testDebugUnitTest/index.html)

![](documentation/unit_tests.png)

## Code Coverage: JaCoCo
[Link here!](https://28-349586620-gh.circle-artifacts.com/0/reports/jacoco/testDebugUnitTestCoverage/html/index.html)

![](documentation/code_coverage.png)

## Ktlint: for kotlin static code analysis
[Link here!](https://28-349586620-gh.circle-artifacts.com/0/reports/ktlint/ktlintDebugSourceSetCheck/ktlintDebugSourceSetCheck.html)

![](documentation/ktlint.png)

## Lint
[Link here!](https://28-349586620-gh.circle-artifacts.com/0/reports/lint/lint-report.html)

![](documentation/lint.png)
