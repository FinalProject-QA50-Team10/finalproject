@ECHO OFF

SET Postman=%cd%

cd %Postman%

@ECHO WEare collections
SET postman_collection=TwoMenAndWomanCollection.json
SET postman_environment=TwoMenAndWomanEnvironment.json

call newman run TwoMenAndWomanCollection.json -e TwoMenAndWomanEnvironment.json -r htmlextra

REM Pause to see the output
PAUSE
