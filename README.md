# Ad Generator App (Xrusi Eukairia)

## Overview

The Ad Generator application, named "Xrusi Eukairia," is a mobile and tablet-friendly app developed in Android Studio. It allows users to create sample ads for products by providing details such as title, price, description and a valid area obtained from a remote API and parse the results in a JSON string.

## Technologies Used

- **Jetpack Compose and Kotlin:** The app's user interface is implemented using Jetpack Compose, a modern Android UI toolkit, and Kotlin programming language.

- **Dagger Hilt:** Dependency injection is handled using Dagger Hilt, providing a clean and modular structure for managing components such as the repository, use cases, and API.

- **Retrofit:** The app utilizes Retrofit to create an instance of the remote API, enabling seamless communication with the server through GET HTTP requests for location information.

- **Coroutines and Flows:** To manage asynchronous operations and mitigate the risk of overusing the remote server, the app uses Coroutines and Flows.

- **Caching with Retrofit:** Caching is implemented with Retrofit to optimize performance by limiting the number of HTTP requests to the server, both in online and offline modes.

- **MVVM Design Pattern:** The app follows the MVVM (Model-View-ViewModel) design pattern, organized into UI, domain, and data packages ensuring a clear separation of concerns.

- **Use Cases:** Complex logic is encapsulated in use cases, injected into the ViewModel which is primarily responsible for managing the UI's state.

- **Unit Testing:** JUnit5 and MockWebserver are employed (along with a fake test double for the repository) for unit and integrated testing the ViewModel, Use Cases and API.

- **Instrumented Testing:** Compose testing framework is utilized (and a little Espresso) for UI and end-to-end testing of the entire screen.


### End-to-End (E2E) Testing Video

[xe_tablet.webm](https://github.com/chrikara/PokemonTcg/assets/80850757/1c758a9b-ef10-4bef-a493-98a9ae2187f9)

## Usage

To run the app, follow these steps:

1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the app on an Android device or emulator.
