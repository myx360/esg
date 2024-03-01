#!/bin/bash

FIRST_LINE_FLAG=true

# Read the CSV file line by line
while IFS=, read -r ref name address1 address2 town county country postcode
do
    $FIRST_LINE_FLAG && export FIRST_LINE_FLAG=false && continue

    # Convert the CSV row to a JSON payload
    JSON_PAYLOAD=$( jq -n \
    --arg name "$name" \
    --arg address1 "$address1" \
    --arg address2 "$address2" \
    --arg town "$town" \
    --arg county "$county" \
    --arg country "$country" \
    --arg postcode "$postcode" \
    '{name: $name, address1: $address1, address2: $address2, town: $town, county: $county, country: $country, postcode: $postcode}' )
    
    echo "JSON Payload: $JSON_PAYLOAD"
    # Define the API endpoint
    API_ENDPOINT="http://localhost:8080/api/customer/$ref"


    # Send the JSON payload to the API endpoint
    curl -X POST -H "Content-Type: application/json" -d "$JSON_PAYLOAD" $API_ENDPOINT
done < esg_data.csv