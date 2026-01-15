# Esercizio tecnico – Dashboard di riconciliazione titoli

Ciao,  
questo esercizio è ispirato a problemi reali su cui lavoriamo in H14.  
Non è un test accademico e non ci aspettiamo una soluzione “completa” o “perfetta”.

Quello che mi interessa davvero capire è:
- come interpreti un problema ambiguo
- che domande ti fai
- come prendi decisioni e stabilisci le priorità
- come motivi le scelte fatte

---

## Contesto

In H14 gestiamo più portafogli di investimento.  
Le posizioni titoli provengono da due fonti principali:

- **posizioni interne**, registrate sulla piattaforma di portfolio management (Swarm)
- **posizioni comunicate dalle banche**, tramite feed di report dei conti titoli

Queste informazioni non sono sempre allineate e devono essere **riconciliate** per:
- individuare discrepanze
- capire cosa manca o non torna
- avere uno stato affidabile della situazione patrimoniale

---

## Obiettivo

Progettare e implementare una **mini applicazione full stack (Spring Boot + React)** che permetta di:

- rappresentare posizioni titoli provenienti da fonti diverse
- confrontarle
- visualizzare lo stato di riconciliazione

L’obiettivo non è la completezza funzionale, ma **il processo che segui per arrivarci**.

---

## Step di complessità aggiuntivi (opzionali)

### A – Posizione H14 su più banche
Una posizione interna può essere composta da titoli detenuti su più banche  
(es. 100 azioni Apple Inc. = 80 Intesa + 20 Mediolanum).

### B – Posizione bancaria su più portafogli
Una posizione su un conto titoli può essere allocata su più portafogli  
(es. 100 azioni Tesla = 30 Tech + 70 USA).

### C – Riconciliazione manuale
Alcune banche non inviano report digitali.  
Prevedere la possibilità di validare manualmente una riconciliazione, tracciando data, utente e nota.

### D – Riconciliazione prezzi
Oltre alle quantità detenute, riconciliare anche l'ultimo prezzo di mercato registrato per ogni titolo.

---

## Requisiti volutamente non specificati

- Identificazione univoca dei titoli
- Frequenza dei report bancari
- Granularità della riconciliazione

Puoi fare assunzioni, semplificare o documentare le domande aperte.

---

## Domande durante l’esercizio

Se emergono dubbi o ambiguità, sei incoraggiato a scrivermi direttamente via email.
Non tutte le domande avranno una risposta definitiva, in certi casi potrei lasciarti aperti diverse interpretazioni per valutare le tue scelte.

---

## Consegna

Struttura consigliata del repository:

/backend  
/frontend  
PROBLEM.md  
README.md  

---

## Semplificazioni ammesse

- dati mock o in memoria
- niente autenticazione
- niente test automatici

L’importante è motivare le priorità.

---

## Uso di AI

Puoi usare qualsiasi strumento, inclusa AI,
purché tu sia in grado di comprendere e spiegare il codice.

---

## Tempo indicativo

4–6 ore.
