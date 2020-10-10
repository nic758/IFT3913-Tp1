# IFT3913-Tp1

Projet qui mesure la sous-documentation d'un code java.

Fait par : Leblanc Nicolas et Abderaouf Malek

Si il est nécessaire d'importer le code github pour faire la correction merci de s'assurer que votre IDE utilise Java 11 ou une version supérieure.


Des librairies externes (com.opencsv.CSVWriter , org.junit.jupiter.api.Assertions) sont utilisées.
## example pour lancer le programme
```
java -jar out/artifacts/IFT3913_Tp1_jar/IFT3913-Tp1.jar -fp src/metric/Console.java
```
## example pour lancer le programme et export les données vers un fichier csv (important, le dossier doit exister)
```
java -jar out/artifacts/IFT3913_Tp1_jar/IFT3913-Tp1.jar -fp ../IFT3913-Tp1/src/metric/Console.java -o test
```
