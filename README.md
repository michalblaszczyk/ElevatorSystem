# ElevatorSystem -> Algorytm
Prosty algorytm FCFS w kolejce obsługi windy nie będzie działał.W przypadku gdy winda jedzie z 2 piętra do 9, a gdy znajduje się na 5 piętrze, winda otrzymała żądanie z piętra 8.
W takim przypadku powinniśmy najpierw zatrzymać windę na 8 piętrze, a następnie jechać do piętra 10. 

Wykorzytany algorytm:
Każda winda ma 2 TreeSety pięter.Pierwszy TreeSet dla wszystkich żądań z pięter powyżej bieżacego piętra w porządku rosnącym.
Drugi TreeSet dla wszystkich żądań z pięter poniżej bieżacego piętra w porządku malejącym.TreeSet pomaga również uniknąć duplikatów żądań.

Ruch windy:
Przypadek pierwszy:
Winda była w stanie NONE, co oznacza, że winda nie obsłużyła jeszcze żadnego żądania lub została wykonana obsługa wszystkich poprzednich żądań w kierunku jej ruchu.
Rozpoczyna obsługę żądania w kierunku, który jest najbliższy piętru z którego jest żądanie i ustaw kierunek w górę lub w dół na podstawie względnej lokalizacji piętra docelowego.

Przypadek drugi:
Winda jedzie w górę. Kontynuuje poruszanie się w tym kierunku, dopóki nie zostaną obsłużone wszystkie piętra powyżej obecnych pięter.
Po zakończeniu obsługi wszystkich powyższych, pięter winda przechodzi do stanu NONE.
    
    
Przypadek trzeci:
Winda jedzie w dół. Kontynuuje poruszanie się w tym kierunku, aż wszystkie piętra poniżej obecnych pięter będą obsłużone.
Po zakończeniu serwisowania wszystkich pięter, winda przechodzi do stanu NONE.

# Metoda pickUP i zbieranie zgłoszeń
Nie przypisuje żądania do windy, jeśli znajduje się ona w stanie MAINTENANCE lub już obsługuje więcej niż średnią liczbę aktywnych żądań w systemie.
Wsród wszystkich wind z wyjątkiem powyższych, znajduje najbliższą windę poruszającą się w kierunku żądania lub jest wolna.

Przypadek pierwszy:
Są 2 windy, 1 powyżej piętra z którego jest żądanie która zjeżdża w dół i 1 poniżej piętra z którego jest żądanie, która jedzie w górę. Metoda przypisuje żądanie do nabliższej z nich.Zwraca prawdę.
                   
Przypadek drugi:
Tylko 1 winda porusza się w kierunku żądania.Przypisuje żądanie do danej windy.Zwraca prawdę.


Przypadek trzeci: 
Żadne windy nie zostały uznane za spełniające wymagania. Może się zdarzyć tak, jeśli wszystkie windy znajdują się w stanie MAINTENANCE.Zwraca fałsz.
