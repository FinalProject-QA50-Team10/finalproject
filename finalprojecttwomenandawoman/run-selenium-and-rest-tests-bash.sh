#!/bin/bash

# Run Selenium and REST tests using Maven
mvn test
mvn surefire-report:report-only
mvn site -DgenerateReports=false

# Pause to see the output
read -p "Press Enter to continue..."
