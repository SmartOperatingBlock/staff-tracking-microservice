# Staff Tracking Microservice 

![Release](https://github.com/smartoperatingblock/staff-tracking-microservice/actions/workflows/build-and-deploy.yml/badge.svg?style=plastic)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=plastic)](https://opensource.org/licenses/MIT)
![Version](https://img.shields.io/github/v/release/smartoperatingblock/staff-tracking-microservice?style=plastic)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=SmartOperatingBlock_staff-tracking-microservice&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=SmartOperatingBlock_staff-tracking-microservice)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=SmartOperatingBlock_staff-tracking-microservice&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=SmartOperatingBlock_staff-tracking-microservice)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=SmartOperatingBlock_staff-tracking-microservice&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=SmartOperatingBlock_staff-tracking-microservice)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=SmartOperatingBlock_staff-tracking-microservice&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=SmartOperatingBlock_staff-tracking-microservice)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=SmartOperatingBlock_staff-tracking-microservice&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=SmartOperatingBlock_staff-tracking-microservice)

## Usage
1. Provide a `.env` file with the following variables:
   - `STAFF_TRACKING_MONGODB_URL`: the mongodb connection string
   - `BOOTSTRAP_SERVER_URL`: the kafka connection endpoint
   - `SCHEMA_REGISTRY_URL`: the schema registry url
2. Run the container with the command:
    ```bash
    docker run ghcr.io/smartoperatingblock/staff-tracking-microservice:latest
    ```

## Documentation
- Check out the website [here](https://smartoperatingblock.github.io/staff-tracking-microservice)
- Check out the _REST-API_ documentation [here](https://smartoperatingblock.github.io/staff-tracking-microservice/documentation/openapi-doc)
- Check out the Code documentation here [here](https://smartoperatingblock.github.io/staff-tracking-microservice/documentation/code-doc)
