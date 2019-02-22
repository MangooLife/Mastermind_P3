# Install

 - Clone or download the repository
 - (IntelliJ) Set modules : File > Project Structure... > Modules
    - Sources : src file
    - Resources : resources file
    - Tests : test file

# Parameters

You can parameter the number of life, the number of cases and set the developer mode.

Go to src/main/resources/config/properties
Here some example :

```
#Game Properties
nbLife = 20
nbCase = 4
isDeveloperMode = 0
```

# How to play
1. Run Main class
2. Choose your game
```
Choisissez un jeu :
1. Recherche +/-
2. Mastermind
```
3. Choose your mode

```
Choisissez un mode de jeu :
1. Mode challenger: devinez la combinaison secrète de l'ordinateur
2. Mode défenseur: laissez l'ordinateur deviner votre comnbinaison secrète
3. Mode duel: vous vs l'ordinateur. Le premier qui trouve la combinaison gagne
```

4. Recherche Plus ou Moins
    ```
    *** Mode Challenger ***
    Veuillez taper une suite de 4 chiffres entre 0 et 9: 1234
    Proposition : 1234 -> Réponse : +++-
    ```
    ```
    *** Mode défenseur ***
    Proposition : 7220 -> Réponse : Est-ce la bonne combinaison ? ===+

    Proposition : 7224 -> Réponse : Est-ce la bonne combinaison ?
    ```
    ```
    *** Mode duel ***
    Joueur devine la combinaison de l'ordinateur :
    Veuillez taper une suite de 4 chiffres entre 0 et 9: 1234
    Proposition : 1234 -> Réponse : =-++

    L'ordinateur devine la combinaison du joueur :
    Proposition : 1058 -> Réponse : Est-ce la bonne combinaison ? +==-
    ```
4. Mastermind
    ```
    *** Mode Challenger ***
    Veuillez taper une suite de 4 chiffres entre 0 et 9: 1234
    Proposition : 1234 -> Réponse : 0 présent, 1 bien placé
    ```
    ```
    *** Mode défenseur ***
    Proposition : 0000 -> Réponse : Combien de chiffre bien placés ? 1
    Combien de chiffre correctes ? 0
    0 présent, 1 bien placé
    ```
    ```
    *** Mode duel ***
    Joueur devine la combinaison de l'ordinateur :
    Veuillez taper une suite de 4 chiffres entre 0 et 9: 1234
    Proposition : 1234 -> Réponse : 1 présent, 1 bien placé
    L'ordinateur devine la combinaison du joueur :
    Proposition : 0000 -> Réponse : Combien de chiffre bien placés ? 3
    Combien de chiffre correctes ? 0
    0 présent, 3 bien placé
    ```
5. Quit the game
```
1. Rejouer à une partie
2. Retour au menu des jeux
3. Quitter Mastermind
```
