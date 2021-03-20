#sudo chmod 755 ./run.sh

# Circle CI command
./gradlew clean buildDebug lintDebug ktlintDebugCheck testDebugUnitTest testDebugUnitTestCoverage --stacktrace

# unit test specific package
# ./gradlew clean testDebugUnitTest  --tests "com.jmarkstar.gumtree_challenge.*"

# unit test specific test class
# ./gradlew clean testDebugUnitTest  --tests "com.jmarkstar.gumtree_challenge.MainActivityTest"

# add ./gradlew --info to see the `println()` logs on the console.