// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "UsbDetect",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "UsbDetect",
            targets: ["UsbDetectorPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "UsbDetectorPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/UsbDetectorPlugin"),
        .testTarget(
            name: "UsbDetectorPluginTests",
            dependencies: ["UsbDetectorPlugin"],
            path: "ios/Tests/UsbDetectorPluginTests")
    ]
)