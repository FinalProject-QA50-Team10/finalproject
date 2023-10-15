call mvn test
call mvn surefire-report:report-only
call mvn site -DgenerateReports=false
PAUSE