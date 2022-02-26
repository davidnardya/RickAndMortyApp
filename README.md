# README #

This project was created by David Nardya on February 2022 for YIT as a homework test.

### Construction of Project ###
* MVVM architecture:
- Using ViewModels for each Activity and Fragment in this project. In these ViewModels I manage the model's logic (such as importing the data using Coroutine Scope) and push them to the UI, which is managed in the activity and fragments.
- Using different repositories managing the ROOM DB and data.
- Models are on separated classes. 
* Retrofit imports the data from RickAndMorty's API.
* Lists of characters and episodes are presented in dedicated Adapters, inflating the single item's layout. 
* HILT for Dependency Injection, using it to inject ViewModel instances in needed activity and fragments. The injection is built in the AppModule class and used in each ViewModel.
* Interface listening to the CharacterAdapter's clicks and moving to next fragment.
* Internet permissions added to Manifest.
* Within the Activity and Fragments I used ViewBinding instead of outdated FindViewById.

### App functionality ###
As requested, this app presents a screen with a list that shows the first page of characters, two characters in each row, from the show Rick and Morty - by name and picture. 
Once the user clicks on the character, a new fragment is opened, presenting the character's basic information and a list of episodes it participated in. 
Once the user clicks on the episode name, the fragment is replaced by a fragment presenting the episode's data, including a list of characters participated in it. 