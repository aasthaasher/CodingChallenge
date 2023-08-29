# CodingChallenge: Star Wars Character Viewer
The Star Wars Character Viewer is an Android app that allows users to explore the vast world of Star Wars characters. You can search for characters, view their details, and mark your favorite ones for quick access. 

<table>
  <tr>
    <td>Live Search</td>
     <td>Character detail</td>
     <td>Adding character to favorite</td>
  </tr>
  <tr>
    <td><img src="https://github.com/aasthaasher/CodingChallenge/assets/47029997/d40285ae-7d0e-447c-ab20-4017f37a30b2" width=240 height=480></td>
    <td><img src="https://github.com/aasthaasher/CodingChallenge/assets/47029997/27363f6d-ede4-41e2-a0b1-86c536de0404" width=240 height=480></td>
    <td><img src="https://github.com/aasthaasher/CodingChallenge/assets/47029997/5b00f77c-1494-4169-8c71-c6c0cee7727a" width=240 height=480></td>
  </tr>
 </table>



## Features

- **Search for Characters**: Use the search bar to find your favorite Star Wars characters. As you type, live search results appear in the RecyclerView.

- **Character Details**: Tap on a character to view their detailed information, including name, height, mass, hair color, skin color, eye color, birth year, and gender.

- **Favorite Characters**: Mark characters as favorites, and they'll be saved locally for easy access.

- **Shared Preferences**: The app uses SharedPreferences to store your list of favorite characters, ensuring your preferences are remembered.

- **Navigation**: Seamlessly switch between the main search view and character details with the built-in navigation system.

## Project Structure

The Star Wars Character Viewer Android app is organized into several packages, each serving a distinct purpose:

- **`adapters`**: Inside this package, you'll find the `CharacterAdapter` class. This adapter is essential for binding character data to the RecyclerView in the main activity, allowing users to scroll through the list of characters.

- **`model`**: The `Character` class resides in this package. It defines the structure of a character, including properties like name, height, mass, and more.
  
- **`network`**: Here, you'll find the `SwapidevService` interface, which defines the API endpoints for retrieving Star Wars character data. The Retrofit library uses this interface to make network requests. Additionally, the `CharacterResponse` class is used to deserialize API responses into character objects.

The two main activities are:
- **MainActivity**: This class represents the main screen of the app, containing the search bar and RecyclerView which shows live search results.
- **DetailActivity**: This class represents the character detail screen, displaying character information and allowing users to mark characters as favorites.
 
## Usage

1. **Launch the App**: Open the app on your Android device.

2. **Search for Characters**: Use the search bar at the top to enter your search query.

3. **View Character Details**: Tap on a character from the search results to see their detailed information.
 
4. **Mark as Favorite**: In the character detail view, use the "Add to favorites" or "Remove from favorites" button to change if the character is favored.

5. **Favorites List**: Favorite characters are highlighted with a "Favorite" status in both the main search view and the character detail view(with a heart next to the name).
