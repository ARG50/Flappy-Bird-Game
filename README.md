# Flappy Bird Game in Java with Swing GUI

This is a simple Flappy Bird game built using **Java** and **Swing GUI**. The game features the classic mechanics of Flappy Bird, where the player controls a bird attempting to avoid pipes and stay in the air by tapping the screen or pressing a key.

## Features

- **Flappy Bird Gameplay:** The bird can be controlled by pressing a key (or tapping the screen) to simulate a flapping motion that keeps the bird in the air.
- **Pipes:** The game features moving pipes that appear at random heights and move from right to left across the screen.
- **Collision Detection:** The bird collides with pipes or the ground, resulting in the end of the game.
- **Background Music and Sound Effects:** There is not added any background music and sound effects for flapping and collisions.

## Requirements

- **Java Development Kit (JDK) 8 or higher** for running the game.
- **Swing Library** (Part of Java SE, no additional setup required).

## Setup and Installation

1. **Clone the repository:**
 `git clone https://github.com/ARG50/Flappy-Bird-Game.git`


2. **Navigate to the project folder:**
`cd flappybird-java`


3. **Compile the Java files:**
If you're using a command line, you can compile the Java files with:
`javac FlappyBird.java`


4. **Run the game:**
After compiling, you can run the game by executing:
`java FlappyBird`


## How to Play

- **Control the Bird:** Press the up arrow to make the bird "flap" and rise.
- **Avoid Pipes:** The pipes are moving from right to left. You need to avoid colliding with them by flapping the bird.
- **Game Over:** The game ends when the bird collides with a pipe or falls to the ground.
- **Restart Game:** The game will begin from start if user press Spacebar key while game is over.
- **Exit Game:** The game will exit if user press Escape Key or 'Esc' while game is over.

## Game Controls

- **Flap the Bird:** Press the **`Up Arrow Key`** to make the bird flap and rise.
- **Restart:** Press **`Spacebar`** to restart the game again.
- **Exit Game:** Press **`Esc`** to exit the game totally.

## Game Assets

- **Images:** The game includes image assets for the background, bird, pipes, and other UI elements. These images should be located in the `resources` directory of the project.
- **Sound Effects:** There is no sound effects used such as bird flapping, pipe collisions, and background music in the game.

Make sure you have the following image files in your `resources` folder:
- `flappybirdbg.png` - Background image.
- `flappybird.png` - Image for the bird.
- `toppipe.png` - Image for the top pipe.
- `bottompipe.png` - Image for the bottom pipe.

### Troubleshooting

- **Image Not Found:**
Ensure that the image files are located in the correct directory (`resources`) and are being referenced correctly in the code.

- **Game Crashes:**
If you see any crashes, check the console for error messages, which might provide insight into missing resources or other issues.

## Contribution & Acknowledgement

- This project was made possible with the help of these amazing resources below.
- **ChatGPT** - Assisted with generating code snippets, debugging, and documentation.
### Assisted with generating code snippets, debugging, and documentation. 
- [ChatGPT](https://openai.com/chatgpt)
  
- Special thanks to these channels:
### Java Frame Design Inspiration 
- [BroCode](https://www.youtube.com/@BroCodez)
### Java Game Design Inspiration &
- [KennyYipCoding](https://www.youtube.com/@KennyYipCoding)

### Reporting Issues

If you encounter any issues or bugs, please open an issue in the GitHub repository.

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

---

## Screenshots

Here you can add a few screenshots of the game in action.

**Example:**

![Flappy Bird Game Screenshot](screenshots/flappybird_screenshot.png)

---

## Acknowledgements

- **Swing GUI:** Java's built-in Swing library was used to create the GUI and handle user interaction.
- **Original Flappy Bird Game:** The inspiration for this project comes from the popular mobile game **Flappy Bird**.

---

Happy coding, and enjoy the game!

