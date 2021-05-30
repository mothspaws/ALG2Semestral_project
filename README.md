# Rezervační systém testů COVID-19

### 1. Zadání práce:
 Vytvořit rezervační systém, který představuje hlavní kancelář pro rezervaci termínů testování na COVID-19. Komunikace s uživatelem probíhá přes konzolu. Uživatel může najít a vybrat místo testování na základě typu testu, který potřebuje (PCR nebo antigen). Program čte soubor o místech konání testu, a také čte a zapisuje soubory ve formátech txt a pdf s informacemi o rezervace.
 
### 2. Návrch řešení
#### 2.1. Funkční specifikace

Informace o testovacích místech byly staženy z oficiální stránky Ministerstva zdravotnictví.

<img width="810" alt="trueObject" src="https://user-images.githubusercontent.com/72342131/120101478-949b2880-c146-11eb-9670-c4fd9fee71fe.png">

#### 2.2. Popis vstupních a vystupních souborů

Informace o testovacích místech se v programu objeví hned po spuštění, uživatel nemusí stahovat další soubory. Funkce skupinové rezervace může být zapotřebí, například pokud je třeba zaregistrovat pracovníky na test. Soubor se seznamem pracovníků je zadán uživatelem spolu s typem souboru (soubor.typ). Soubor by se měl skládat ze tří sloupců: v prvním - ID místa testování, Jméno a příjmení oddělené mezerou a datum, na které zaměstnanec chce zapsat. Sloupce jsou odděleny středníkem.
Program vytváří soubory s testovacími místy a seznamy zálohovaných datumů. Soubor s testovacími místy (systémové ID, název, adresa) má formát txt. Soubory se seznamy rezervovaných datumů (ID místa testování, jméno a příjmení, datum) mohou být jak ve formátu txt, tak ve formátu pdf.

#### 2.3. Class diagram

![classDiagram](https://user-images.githubusercontent.com/72342131/120101481-98c74600-c146-11eb-94e8-44b52a1a391f.png)

### 3. Popis fungování externí knihovny

Externí knihovna pdfbox umožňuje vytvářet dokumenty typu pdf. Pomocí této knihovny se vytvoří prázdná stránka dokumentu a postupně se přidají textová data nebo obrázky.

### 4. Testy

<img width="687" alt="table" src="https://user-images.githubusercontent.com/72342131/120101489-9d8bfa00-c146-11eb-9b94-3bd5f2e1f677.png">

#### 1.

<img width="439" alt="1" src="https://user-images.githubusercontent.com/72342131/120101498-a54b9e80-c146-11eb-8f2b-9ffda72900d1.png">

#### 2.

<img width="300" alt="2" src="https://user-images.githubusercontent.com/72342131/120101501-aaa8e900-c146-11eb-8dbe-cfda62f51075.png">

#### 3.

<img width="261" alt="3" src="https://user-images.githubusercontent.com/72342131/120101505-ad0b4300-c146-11eb-85fe-a2d02b933509.png">

#### 4.

<img width="427" alt="4" src="https://user-images.githubusercontent.com/72342131/120101508-af6d9d00-c146-11eb-84d7-d3b836a1fb96.png">

#### 5.

<img width="497" alt="5" src="https://user-images.githubusercontent.com/72342131/120101511-b1376080-c146-11eb-83c5-e55feb65262e.png">

#### 6. 

<img width="492" alt="6" src="https://user-images.githubusercontent.com/72342131/120101515-b399ba80-c146-11eb-82ad-a1b31530fcce.png">

#### 7. 

<img width="308" alt="7" src="https://user-images.githubusercontent.com/72342131/120101517-b5637e00-c146-11eb-99c2-4aed2cb2a0e7.png">

#### 8. 

<img width="528" alt="8" src="https://user-images.githubusercontent.com/72342131/120101523-b98f9b80-c146-11eb-859d-045fa9cf1ee4.png">

#### 9. 

<img width="258" alt="9" src="https://user-images.githubusercontent.com/72342131/120101526-bdbbb900-c146-11eb-9149-d6fd7b9a8449.png">

#### 10.

<img width="821" alt="10" src="https://user-images.githubusercontent.com/72342131/120101532-c0b6a980-c146-11eb-8db9-2908d5c4ce1d.png">
