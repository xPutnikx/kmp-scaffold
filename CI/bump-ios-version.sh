#!/bin/bash
set -e

XCCONFIG_FILE="iosApp/Configuration/Config.xcconfig"

if [ ! -f "$XCCONFIG_FILE" ]; then
    echo "Error: $XCCONFIG_FILE not found"
    exit 1
fi

# Get current version
CURRENT_NAME=$(grep 'VERSION_NAME' gradle.properties | cut -d'=' -f2)
CURRENT_BUILD=$(grep 'CURRENT_PROJECT_VERSION' "$XCCONFIG_FILE" | cut -d'=' -f2 | tr -d ' ')
NEW_BUILD=$((CURRENT_BUILD + 1))

echo "Current: version=$CURRENT_NAME, build=$CURRENT_BUILD"
echo "New:     build=$NEW_BUILD"

# Run shared tests
echo "Running tests..."
./gradlew :composeApp:jvmTest || {
    echo "Tests failed! Aborting version bump."
    exit 1
}

# Update Xcode config
sed -i '' "s/CURRENT_PROJECT_VERSION = $CURRENT_BUILD/CURRENT_PROJECT_VERSION = $NEW_BUILD/" "$XCCONFIG_FILE"
sed -i '' "s/MARKETING_VERSION = .*/MARKETING_VERSION = $CURRENT_NAME/" "$XCCONFIG_FILE"

echo "Updated iOS build to $NEW_BUILD"

# Create git tag
git add "$XCCONFIG_FILE"
git commit -m "Bump iOS build number to $NEW_BUILD"
git tag "ios-v${CURRENT_NAME}-${NEW_BUILD}"

echo "Tagged: ios-v${CURRENT_NAME}-${NEW_BUILD}"
