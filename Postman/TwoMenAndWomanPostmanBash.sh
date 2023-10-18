#!/bin/bash

# Get the current directory
Postman=$(pwd)

# Change to the Postman directory
cd "$Postman"

echo "WE are collections"

# Set the collection and environment file paths
postman_collection="TwoMenAndWomanCollection.json"
postman_environment="TwoMenAndWomanEnvironment.json"

# Run Newman with the specified collection and environment
newman run "$postman_collection" -e "$postman_environment" -r htmlextra

# Pause to see the output
read -p "Press Enter to continue...
