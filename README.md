# Harry Potter App

## Installation 

As always, clone and open in Android studio.
`git clone https://github.com/jamesscully/HarryPotterApp`

## Features
- Display in Alphabetical order
- Save data to Room database (populates if no database exists)
- Sort by staff/student
- Sort by House

## Dependencies
As required:
- Retrofit 2
- Kotlin Coroutines

## Notes
I didn't implement loading from a Room database via secondary source from a Repository, however I have implemented populating the database (as seen in `CharacterDatabase.kt` via `MainActivity.kt`).


You can find the Room Database via Database Inspector or 
`/data/data/com.example.harrypottercharacters/databases/characters.db`
after initial launch of the application.

Happy [bug] hunting!

