# CodingChallenge: Star Wars Character Viewer
The Star Wars Character Viewer is an Android app that allows users to explore the vast world of Star Wars characters. You can search for characters, view their details, and mark your favorite ones or save them for quick access. 

<table>
  <tr>
    <td>Live Search</td>
    <td>Saved characters</td>
     <td>Character detail</td>
     <td>Adding to favorite and saved</td>
  </tr>
  <tr>
    <td><img src="https://github.com/aasthaasher/CodingChallenge/assets/47029997/41768826-6a1d-4400-b326-8ded29f63569" width=240 height=480></td>
    <td><img src="https://github.com/aasthaasher/CodingChallenge/assets/47029997/fe21b577-831f-4a48-be67-d759cdfc8914" width=240 height=480></td>
    <td><img src="https://github.com/aasthaasher/CodingChallenge/assets/47029997/b3a1c177-9712-49e3-bf60-7c7e3fd9cff6" width=240 height=480></td>
    <td><img src="https://github.com/aasthaasher/CodingChallenge/assets/47029997/f278346c-a54a-45e2-9d01-a45c76524e86" width=240 height=480></td>
  </tr>
 </table>




## Features

- **Search for Characters**: Use the search bar to find your favorite Star Wars characters. As you type, live search results appear in the RecyclerView.

- **Character Details**: Tap on a character to view their detailed information, including name, height, mass, hair color, skin color, eye color, birth year, and gender.

- **Favorite Characters**: Mark characters as favorites, and they'll be saved locally for easy access.

- **Shared Preferences**: The app uses SharedPreferences to store your list of favorite characters, ensuring your preferences are remembered.

- **Saved Characters**: Save characters in ROOM database

- **Navigation**: Seamlessly switch between the main search view and character details with the built-in navigation system.

## Project Structure

The Star Wars Character Viewer Android app follows the MVVM (Model-View-ViewModel) architecture, which is divided into three primary packages: model, view, and viewModel.

- **`model`**: The model package handles data-related operations and network communication. It contains sub-packages and classes for data management:
    - **`model.data`**:  Contains classes that define the structure of Star Wars characters and manage their storage in the app's database. It includes the following classes:
      -  **`Character`**: This class defines the structure of a Star Wars character. It includes properties such as name, height etc.
      -  **`CharacterDao`**: The CharacterDao interface is part of the Room Persistence Library and serves as the Data Access Object for the CharacterEntity class. It defines methods for performing database operations, such as inserting, updating, deleting, and querying characters.
      -  **`CharacterDatabase`**:  CharacterDatabase is a Room Database class that acts as the database holder. It defines the database's schema and provides access to the DAO (Data Access Object) interfaces. This class helps manage the SQLite database used to store character data.
      -  **`CharacterEntity`**:CharacterEntity is an Entity class that represents a character in the database. It mirrors the structure of the Character class but is used for database storage. Instances of this class correspond to character records in the database and can be inserted, updated, and retrieved using Room
  
  - **`model.network`**: Manages network-related classes such as SwapidevService for defining API endpoints and CharacterResponse for deserializing API responses.
  - **`model.repository`**: Contains the CharacterRepository, which manages data retrieval and provides an abstraction layer for data sources.
    
- **`view`**: The view package is responsible for the user interface and user interaction. It includes the following components:
  - **`views`**: Houses the main activities:
    - **MainActivity**: This class represents the main screen of the app, containing the search bar and RecyclerView which shows live search results which included the characters' status as favorite and/or saved. Also includes a button to see the list of saved characters.
    - **DetailActivity**: This class represents the character detail screen, displaying character information and allowing users to mark characters as favorites and/or saved.
  - **`views.adapters`**: Contains the CharacterAdapter class, essential for binding character data to the RecyclerView in the main activity.

- **`viewModel`**: The viewModel package contains the CharacterViewModel, responsible for managing UI-related data and handling user interactions. This ViewModel class communicates with the repository to provide data to the UI.

## Usage

1. **Launch the App**: Open the app on your Android device.

2. **Search for Characters**: Use the search bar at the top to enter your search query.

3. **View Character Details**: Tap on a character from the search results to see their detailed information.
 
4. **Mark as Favorite**: In the character detail view, use the "Add to favorites" or "Remove from favorites" button to change if the character is favored.
   
6. **Mark as Saved**: In the character detail view, use the "Add to database" or "Remove from database" button to change if the character is saved.

7. **Favorites List**: Favorite characters are highlighted with a "Favorite" status in both the main search view and the character detail view(with a heart next to the name).
8. **Saved Character**: Saved characters are highlighted with a "Saved character" status in both the main search view
9. **All Saved Characters**: All saved characters can be seen in a message box by clicking the "Show Characters in DB" button on the Main Activity
