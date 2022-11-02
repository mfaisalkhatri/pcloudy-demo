![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)

## Don't forget to give a :star: to make the project popular.

## :question: What is this Repository about?

- This repo contains example code of running parallel web automation tests using Selenium WebDriver, TestNG on
  pCloudy platform.
- This project uses Maven as build tool and TestNG framework to run the tests.

## Running the Tests

By default, tests would be running parallel on pCloudy Platform on Chrome and Firefox  Browsers.

- os, os version, browser name and its respective version needs to be set through `paralle.config.json` file.
Tests.
- `id` field in `parallel.config.json` should match with the `parameter value` in testng.xml.

1. To run the test from command line use the following command:

    - `mvn test -Dusername=<pCloudy username> -Dapikey<pCloudy browser cloud api access key>`

2. To run the tests using TestNG in intelliJ:

    - Right click on `testng.xml` and select `Run '...\testng.xml`
      username and access key in the VM option as follows:
      -Dusername=<pCloudy username>
      -Dapikey=<pCloudy browser cloud api access key>

    - Click on Apply and OK to start running the tests.

🧬 Need Assistance?

- Discuss your queries by writing to me @ `mohammadfaisalkhatri@gmail.com`
  OR ping me on any of the social media sites using the below link:
    - [Linktree][linktree]

## :thought_balloon: Checkout the blogs related to Testing on my [website][] and [medium-account][medium]

[linktree]: https://linktr.ee/faisalkhatri

[website]: https://mfaisalkhatri.github.io

[medium]: https://medium.com/@iamfaisalkhatri