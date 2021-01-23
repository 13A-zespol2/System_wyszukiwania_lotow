*95# System_wyszukiwania_lotow

LISTA FUNKCJONALNOŚCI:

1.	Rejestracja użytkownika
	Użytkownik po włączeniu aplikacji będzie miał możliwość założenia konta. Założenie konta będzie polegało na kliknięciu w guzik „Zarejestruj Się”, wypełnieniu pól które zostaną mu wyświetlone:
		E-mail – który będzie służył później jako login.
		Hasło
	Następnie po kliknięciu w guzik „Zarejestruj się”, który będzie znajdował się pod wszystkimi polami zostanie wyświetlona informacja o tym czy wprowadzone dane są poprawne, założone konto będzie aktywne od razu. 
Po założeniu konta i zalogowaniu się na nie użytkownik będzie miał możliwość:
		Wyszukiwania lotów
		Rezerwacji Biletu
		Wgląd w historię swoich zakupionych dotychczas biletów
		Wyświetlenie danych osobowych
		Edycja swoich danych (poza edycją e-mail który służy do logowania się)

2.	Logowanie się użytkownika:
		Użytkownik do logowania się będzie wykorzystywał e-mail (jako login) podany przy rejestracji, oraz hasło. Po wciśnięciu w guzika „zaloguj się” zostanie on przeniesiony do panelu głównego, w którym będą się znajdować wyżej wymienione opcje (Dane osobowe itp.).
	 W przypadku wprowadzenia nie poprawnych danych zostanie wyświetlony odpowiedni komunikat oraz będzie musiał wpisać dane ponownie.

3.	Edycja konta użytkownika:
		Użytkownik po wejściu w zakładkę „Edycja” zostanie przekierowany do widoku, który będzie przedstawiał jego dane aktualne wyświetlone w polach edytowalnych. Użytkownik będzie mógł edytować te dane oraz następnie po wciśnięciu przycisku „Edytuj”, który znajduje się poniżej wszystkich danych Dane wprowadzone przez użytkownika będą przechodziły przez walidację danych (za krótkie hasło itp.)  jeśli nie przejdą walidacji zostanie mu wyświetlony komunikat, że dane zostały źle wprowadzone. Po wprowadzeniu  poprawnych danych zostaną one zmienione w bazie danych. Nie będzie można edytować adresu email, ponieważ służy on jako login.
4.	Wyszukanie lotu:
		Wyszukania lotu będzie mógł dokonać użytkownik bez konta jak i z kontem. Użytkownik bez konta będzie mógł jedynie wyszukać loty w celu sprawdzenia połączeń o podanych przez niego danych, lecz nie będzie miał możliwości dokonania rezerwacji .Wyszukiwanie będzie polegać na wyborze lotniska początkowego oraz docelowego oraz podaniu daty. Po wprowadzeniu danych zostanie wyświetlona lista propozycji różnych linii lotniczych która zostanie pobrana z użytego przez nas API.
5.	Rezerwacja biletu:
		Po wybraniu oferty przez użytkownika będzie on mógł zarezerwować bilet na dany lot. Aby tego dokonać będzie musiał nacisnąć przycisk „Rezerwuj” przy uprzednio wybranej według preferencji użytkownika ofercie. Zostanie mu wyświetlony widok w którym użytkownik będzie podawał dane osobowe pasażera. 
	Po dokonaniu Rezerwacji i wpisaniu danych osobowych zostanie wyświetlona formatka z podsumowaniem całej Rezerwacji. Podsumowanie będzie zawierać:
			Lotnisko początkowe 
			Lotnisko końcowe
			Datę wylotu
			Informacje o ilości zakupionych biletów (jeżeli jest więcej niż jeden)
			Informację o danych osobowych na kogo zostały zarezerwowane bilety
			Cenę końcową 
		Użytkownik będzie miał możliwość dokupienia dodatkowego bagażu oraz pierwszeństwa wejścia na pokład samolotu. Cena zostanie wtedy ustalona adekwatnie do wybranych opcji dodatkowych.

6.	Historia zakupu biletów:
		W panelu „Mój Profil” użytkownik będzie miał możliwość przejścia do zakładki „Historia rezerwacji” po naciśnięciu tej opcji zostaną wyświetlone dotychczas zarezerwowane bilety użytkownika.
