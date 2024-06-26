# Donchyk Maksym Ocado Technology test task
![Static Badge](https://img.shields.io/badge/Java-17?style=flat) ![Static Badge](https://img.shields.io/badge/JGraphT-1.5.2-purple?style=flat)


### Wstęp
W naszym internetowym supermarkecie chcemy rozszerzyć asortyment produktów,
które sprzedajemy o artykuły niespożywcze, takie jak elektronika czy przedmioty do domu.
Charakteryzują się one tym, że często nie da się ich dostarczyć naszym standardowym
samochodem dostawczym, ponieważ są one często po prostu za duże. Drugim powodem
bywa to, że będziemy również oferować przedmioty, które znajdują się u zewnętrznych
dostawców i nie możemy ich dostarczać naszą flotą. W obu takich przypadkach, zakupy
klienta trzeba wysłać specjalistycznym kurierem. Z drugiej strony, oczywistym jest to, że
kurierzy nie mogą dostarczać wszystkich przedmiotów które sprzedajemy, w szczególności
produktów spożywczych.
W związku z tym, że nie wszystkie produkty da się wysłać wszystkimi sposobami
dostawy, musimy podzielić przedmioty w internetowym koszyku klienta na grupy dostaw.
Zadanie polega na optymalnym podziale przedmiotów w koszyku tak, aby zminimalizować
liczbę wymaganych dostaw. Po wyznaczeniu minimalnej liczby grup dostaw, należy upewnić
się, że będziemy tworzyć największe możliwe grupy dostaw (patrz rysunek 1.).
Zaobserwowaliśmy, że klienci często decydują się tylko na zakup przedmiotów z największej
grupy dostawy, pozostawiając resztę w koszyku.

## Zadanie
Twoim zadaniem jest stworzenie biblioteki, która podzieli przedmioty w koszyku klienta na
grupy dostaw. Zdefiniowaliśmy już API, które chcielibyśmy wykorzystać w naszym programie
- patrz “Struktura programu i testowanie”. W celu poprawnego działania, biblioteka musi
wczytać plik konfiguracyjny, który zawiera możliwe sposoby dostawy wszystkich
oferowanych w sklepie produktów. Jako że ta konfiguracja nie zmienia się często,
przechowujemy ją w pliku, który ma zostać przeczytany przez Twoją implementację
biblioteki.

## Implementation

Code is using graphs to find the best possible solution for that problem. Methods ** split(...) ** create a graph with delivery options and products in the basket as vertex and connect them accordingly to the config file passed in the constructor of the class **BasketSplitter**. Then it finds the delivery vertex with the biggest count of edges(connected to the highest amount of products in the basket), creates an element of the final hashMap with key *delivery method* and values (array of vertexes of items connected to that delivery method) and then deletes them. It continues until all the edges are deleted(there are no more products left without the delivery method). Choosing the delivery vertex with the biggest amount of connected items' vertexes ensures that the biggest possible groups are created.
