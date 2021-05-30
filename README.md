# Rezervační systém testu COVID-19

1. Zadání práce:
 Vytvořit rezervační systém, který představuje hlavní kancelář pro rezervaci termínů testování na COVID-19. Komunikace s uživatelem probíhá přes konzolu. Uživatel může najít a vybrat místo testování na základě typu testu, který potřebuje (PCR nebo antigen). Program čte soubor o místech konání testu, a také čte a zapisuje soubory ve formátech txt a pdf s informacemi o rezervace.
 
2. Návrch řešení
2.1. Funkční specifikace
Informace o testovacích místech byly staženy z oficiální stránky Ministerstva zdravotnictví.
soubor
2.2. Popis vstupních a vystupních souborů
Informace o testovacích místech se v programu objeví hned po spuštění, uživatel nemusí stahovat další soubory. Funkce skupinové rezervace může být zapotřebí, například pokud je třeba zaregistrovat pracovníky na test. Soubor se seznamem pracovníků je zadán uživatelem spolu s typem souboru (soubor.typ). Soubor by se měl skládat ze tří sloupců: v prvním - ID místa testování, Jméno a příjmení oddělené mezerou a datum, na které zaměstnanec chce zapsat. Sloupce jsou odděleny středníkem.
Program vytváří soubory s testovacími místy a seznamy zálohovaných datumů. Soubor s testovacími místy (systémové ID, název, adresa) má formát txt. Soubory se seznamy rezervovaných datumů (ID místa testování, jméno a příjmení, datum) mohou být jak ve formátu txt, tak ve formátu pdf.
2.3. Class diagram
soubor
3. Popis fungování externí knihovny
Externí knihovna pdfbox umožňuje vytvářet dokumenty typu pdf. Pomocí této knihovny se vytvoří prázdná stránka dokumentu a postupně se přidají textová data nebo obrázky.
5. Testy
