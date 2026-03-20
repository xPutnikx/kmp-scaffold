#!/bin/bash
set -e

TOML_FILE="gradle/libs.versions.toml"

if [ ! -f "$TOML_FILE" ]; then
    echo "Error: $TOML_FILE not found"
    exit 1
fi

# Get current version
CURRENT_CODE=$(grep 'ANDROID_VERSION_CODE' gradle.properties | cut -d'=' -f2)
CURRENT_NAME=$(grep 'VERSION_NAME' gradle.properties | cut -d'=' -f2)
NEW_CODE=$((CURRENT_CODE + 1))

echo "Current: versionCode=$CURRENT_CODE, versionName=$CURRENT_NAME"
echo "New:     versionCode=$NEW_CODE"

# Run tests first
echo "Running tests..."
./gradlew :composeApp:jvmTest :composeApp:testDebugUnitTest || {
    echo "Tests failed! Aborting version bump."
    exit 1
}

# Update version code
sed -i '' "s/ANDROID_VERSION_CODE=$CURRENT_CODE/ANDROID_VERSION_CODE=$NEW_CODE/" gradle.properties

echo "Updated versionCode to $NEW_CODE"

# Create git tag
git add gradle.properties
git commit -m "Bump Android versionCode to $NEW_CODE"
git tag "android-v${CURRENT_NAME}-${NEW_CODE}"

echo "Tagged: android-v${CURRENT_NAME}-${NEW_CODE}"
