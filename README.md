Jag använde en prompt till ChatGPT för att få hjälp med att skapa JUnit-tester med Mockito.

Exempel på prompt jag använde:
"Hur kan jag skapa ett JUnit-test med Mockito som testar att en metod i GameStoreService anropar en lista med add()?"

Resultat:
ChatGPT förklarade hur man använder @ExtendWith(MockitoExtension.class) och @Mock för att skapa mock-objekt, samt hur man verifierar metodanrop med verify().
Jag använde sedan det svaret för att skapa min testklass GameStoreServiceTest.java.
