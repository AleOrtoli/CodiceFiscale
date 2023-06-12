package com.example.codicefiscale

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import android.widget.AutoCompleteTextView
import android.widget.ArrayAdapter
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cityMap = mapOf(
            "Milano" to "MI", "Roma" to "RO", "Napoli" to "NA", "Torino" to "TO", "Palermo" to "PA", "Genova" to "GE", "Bologna" to "BO", "Firenze" to "FI", "Bari" to "BA",  "Catania" to "CT", "Venezia" to "VE", "Verona" to "VR", "Messina" to "ME",
            "Padova" to "PD", "Trieste" to "TS", "Brescia" to "BS", "Taranto" to "TA", "Prato" to "PO", "Reggio Calabria" to "RC", "Modena" to "MO", "Parma" to "PR",
            "Perugia" to "PG", "Ravenna" to "RA", "Livorno" to "LI", "Cagliari" to "CA", "Foggia" to "FG", "Rimini" to "RN", "Salerno" to "SA", "Ferrara" to "FE", "Sassari" to "SS", "Latina" to "LT", "Giugliano in Campania" to "NA", "Monza" to "MB", "Siracusa" to "SR",
            "Pescara" to "PE", "Bergamo" to "BG", "Forlì" to "FC", "Trento" to "TN", "Vicenza" to "VI", "Terni" to "TR", "Bolzano" to "BZ", "Novara" to "NO", "Piacenza" to "PC", "Ancona" to "AN", "Andria" to "BT",  "Udine" to "UD",  "Arezzo" to "AR", "Cesena" to "FC",  "Lecce" to "LE", "Pesaro" to "PU",  "Barletta" to "BT",  "Alessandria" to "AL",
            "La Spezia" to "SP", "Pistoia" to "PT",  "Guidonia Montecelio" to "RM", "Lucca" to "LU",  "Catanzaro" to "CZ",  "Brindisi" to "BR",  "Torre del Greco" to "NA",  "Treviso" to "TV",
            "Busto Arsizio" to "VA",  "Como" to "CO",  "Marsala" to "TP", "Grosseto" to "GR",  "Sesto San Giovanni" to "MI",  "Varese" to "VA",  "Fiumicino" to "RM", "Pozzuoli" to "NA",
            "Casoria" to "NA", "Asti" to "AT", "Cinisello Balsamo" to "MI", "Caserta" to "CE", "Gela" to "CL", "Aprilia" to "LT", "Ragusa" to "RG", "Pavia" to "PV", "Cremona" to "CR", "Carpi" to "MO", "Quartu Sant'Elena" to "CA",
            "Lamezia Terme" to "CZ", "Altamura" to "BA", "Imola" to "BO", "Pisa" to "PI", "Vigevano" to "PV", "Carrara" to "MS", "Molfetta" to "BA",
            "Faenza" to "RA", "Bisceglie" to "BT", "Trapani" to "TP", "Caltanissetta" to "CL", "Lodi" to "LO", "Matera" to "MT", "Bitonto" to "BA", "Cuneo" to "CN",
            "Bagheria" to "PA", "Portici" to "NA", "Sanremo" to "IM", "Teramo" to "TE", "Avellino" to "AV", "Modica" to "RG", "Potenza" to "PZ", "Acerra" to "NA",
            "Trani" to "BT", "Ercolano" to "NA", "Siena" to "SI", "Chieti" to "CH", "Pomezia" to "RM", "Jesi" to "AN", "Nicastro" to "CZ", "Cava de' Tirreni" to "SA",
            "Cesena" to "FC", "Caltagirone" to "CT", "Mira" to "VE", "Vittoria" to "RG", "Velletri" to "RM", "Gallarate" to "VA", "Scafati" to "SA", "Bassano del Grappa" to "VI", "Lamezia Terme" to "CZ",
            "Olbia" to "OT", "Benevento" to "BN", "Gravina in Puglia" to "BA", "Civitavecchia" to "RM", "Vasto" to "CH", "Casalnuovo di Napoli" to "NA",
            "San Severo" to "FG", "Pordenone" to "PN", "Rovigo" to "RO", "Caltanissetta" to "CL", "Fano" to "PU", "Cerignola" to "FG", "Mazara del Vallo" to "TP", "Rimini" to "RN", "Voghera" to "PV", "Muggiò" to "MB",
            "Nocera Inferiore" to "SA", "Bitonto" to "BA", "Borgomanero" to "NO", "Trezzano sul Naviglio" to "MI", "San Donà di Piave" to "VE", "Seregno" to "MB", "Rho" to "MI", "Acireale" to "CT", "Anzio" to "RM", "Montesilvano" to "PE",
            "Manfredonia" to "FG", "Tivoli" to "RM", "Foligno" to "PG", "Agrigento" to "AG", "Aquila" to "AQ", "Moncalieri" to "TO", "Massa" to "MS", "Viterbo" to "VT",
            "Cosenza" to "CS", "Marano di Napoli" to "NA", "Crotone" to "KR", "Savona" to "SV", "Legnano" to "MI", "Viareggio" to "LU", "Afragola" to "NA", "Castellammare di Stabia" to "NA", "Corgliano-Rossano" to "CS")

        val paesiEU = arrayOf("Albania", "Andorra", "Austria", "Bielorussia", "Bosnia ed Erzegovina", "Bulgaria", "Cipro", "Croazia", "Danimarca",
            "Estonia", "Grecia", "Irlanda", "Islanda", "Kosovo", "Lettonia", "Liechtenstein", "Lituania",
            "Lussemburgo", "Macedonia del Nord", "Malta", "Moldavia", "Monaco", "Montenegro", "Norvegia", "Paesi Bassi", "Polonia", "Portogallo",
            "Regno Unito", "Repubblica Ceca", "Romania", "San Marino", "Serbia", "Slovacchia", "Slovenia", "Svizzera", "Ucraina", "Ungheria", "Vaticano", "Repubblica Socialista Federale di Jugoslavia")

        val paesiEU2 = mapOf("Spagna" to "Spain", "Germania" to "Germany", "Francia" to "France", "Svezia" to "Sweden", "Belgio" to "Belgium", "Finlandia" to "Finland")

        val paesiEE = arrayOf("Afghanistan", "Algeria", "Andorra", "Angola", "Antigua e Barbuda", "Arabia Saudita", "Argentina", "Armenia",
            "Australia", "Bahamas", "Bahrein", "Bangladesh", "Barbados", "Belize", "Benin", "Bhutan", "Bielorussia", "Bolivia",
            "Bosnia ed Erzegovina", "Botswana", "Brasile", "Brunei", "Burkina Faso", "Burundi", "Cambogia", "Camerun", "Canada", "Canarie", "Capo Verde",
            "Ciad", "Cile", "Cina", "Cipro", "Colombia", "Comore", "Congo", "Corea del Nord", "Corea del Sud", "Costa d'Avorio", "Costa Rica",
            "Croazia", "Cuba", "Danimarca", "Dominica", "Ecuador", "Egitto", "El Salvador", "Emirati Arabi Uniti", "Eritrea", "Estonia", "Eswatini",
            "Etiopia", "Figi", "Filippine", "Gabon", "Gambia", "Georgia", "Ghana", "Giamaica", "Giappone", "Gibuti", "Giordania",
            "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guinea Equatoriale", "Guyana", "Haiti", "Honduras", "India", "Indonesia", "Iran",
            "Iraq", "Islanda", "Isole Marshall", "Isole Salomone", "Isole Seychelles", "Isole Tonga", "Isole Tuvalu", "Israele", "Kazakistan",
            "Kenya", "Kirghizistan", "Kiribati", "Kuwait", "Laos", "Lesotho", "Libano", "Liberia", "Libia", "Liechtenstein", "Lituania",
            "Macedonia del Nord", "Madagascar", "Madera", "Malawi", "Maldive", "Malesia", "Mali", "Malta", "Marocco", "Mauritania", "Mauritius", "Messico",
            "Micronesia", "Moldavia", "Monaco", "Mongolia", "Montenegro", "Mozambico", "Myanmar", "Namibia", "Nauru", "Nepal", "Nicaragua", "Niger",
            "Nigeria", "Norvegia", "Nuova Zelanda", "Oman", "Pakistan", "Palau", "Panama", "Papua Nuova Guinea", "Paraguay", "Perù", "Qatar",
            "Repubblica Ceca", "Repubblica Democratica del Congo", "Repubblica Dominicana", "Romania", "Ruanda", "Russia", "Saint Kitts e Nevis",
            "Saint Lucia", "Saint Vincent e Grenadine", "Samoa", "Sao Tomé e Principe", "Senegal", "Serbia", "Seychelles", "Sierra Leone",
            "Singapore", "Siria", "Somalia", "Sri Lanka", "Stati Uniti d'America", "Sudafrica", "Sudan", "Sudan del Sud",
            "Suriname", "Tagikistan", "Tanzania", "Thailandia", "Timor Est", "Togo", "Tonga", "Trinidad e Tobago", "Tunisia",
            "Turchia", "Turkmenistan", "Tuvalu", "Uganda", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Yemen del Sud", "Zambia", "Zimbabwe")

        val Soppresse = arrayOf("Abetone", "Acquacanina", "Acquarica del Capo", "Alice Superiore", "Alluvioni Cambiò", "Amblar", "Arzene", "Auditore", "Barbarano Vicentino", "Barberino Val d'Elsa", "Barchi",
            "Bardello", "Bastida de' Dossi", "Bazzano", "Bellagio", "Berra", "Bersone", "Bezzecca", "Bigarello", "Bolbeno", "Bondo", "Borgoforte", "Borgofranco sul Po", "Bosentino", "Bregano", "Breguzzo", "Breia",
            "Brembilla", "Brez", "Brione", "Busana", "Ca' d'Andrea", "Cadrezzate", "Cagno", "Cagnò", "Calavino", "Camairago",  "Caminata", "Camo", "Campiglia Cervo", "Campolongo sul Brenta", "Canevino", "Carano",
            "Carbonara di Po", "Carpasio", "Casasco d'Intelvi", "Casciana Terme",  "Casole Bruzio", "Cassano Spinola", "Castel Colonna", "Castelfondo", "Castelfranco di Sopra ", "Castellar", "Castellavazzo",
            "Castello di Serravalle", "Castiglione d'Intelvi", "Cavacurta", "Cavaglio-Spoccia", "Cavallasca", "Cecoslovacchia", "Cellio", "Cembra", "Centa San Nicolò", "Cerreto Castello", "Cimego", "Cismon del Grappa",
            "Civenna", "Cloz", "Colbordolo", "Collagna", "Concei", "Conco", "Condino", "Consiglio di Rumo", "Contarina", "Coredo", "Corigliano Calabro", "Cornale", "Corteolona", "Crespano del Grappa", "Crespellano",
            "Crespina", "Crosa", "Cuccaro Monferrato", "Cunevo", "Cursolo-Orasso", "Cutigliano", "Daiano", "Daone", "Darè", "Dimaro", "Don", "Donada", "Dorsino", "Drezzo", "Drizzona", "Fabbriche di Vallico", "Faedo",
            "Falmenta", "Farra d'Alpago", "Faver", "Felonica", "Fiera di Primiero", "Figline Valdarno", "Fiordimonte", "Fiumicello", "Flavon", "Fondo", "Formignana", "Forno di Zoldo", "Gattico", "Gavazzana",
            "Genzone", "Germasino", "Gerosa", "Gironico", "Giuncugnano", "Granaglione", "Grancona", "Grauno", "Gravedona", "Grumes", "Incisa in Val d'Arno", "Introzzo", "Ivano Fracena", "Lanzo d'Intelvi", "Lardaro", "Lari",
            "Lasino", "Laterina", "Lenno", "Lentiai", "Lessona", "Ligonchio", "Ligosullo", "Lisignago", "Longarone", "Lorenzana", "Lu", "Lugnacco", "Lusiana", "Maccagno", "Malgesso", "Malosco", "Mason Vicentino", "Massa Fiscaglia",
            "Megliadino San Fidenzio", "Mel", "Menarola", "Meugliano", "Mezzani", "Mezzegra", "Migliarino", "Migliaro", "Mirabello", "Molina di Ledro", "Molvena", "Monclassico", "Montagne", "Montalcino", "Montalto Ligure", "Monte Colombo",
            "Monteciccardo", "Montemaggiore al Metauro", "Monterado", "Montescudo", "Monteveglio", "Montoro Inferiore", "Montoro Superiore", "Moransengo", "Mossano", "Mosso", "Nanno", "Nave San Rocco", "Nibbiano", "Nicastro", "Orciano di Pesaro",
            "Osmate","Ossuccio", "Padergnone", "Paderno del Grappa", "Parè", "Pecco", "Pecorara",  "Pedace", "Pellio Intelvi", "Perego", "Pergine Valdarno", "Piadena", "Piagge", "Pian di Sco", "Pieve d'Alpago", "Pieve di Bono", "Pieve di Coriano",
            "Pieve di Ledro", "Pievebovigliana", "Pino sulla Sponda del Lago Maggiore", "Piovera", "Piteglio", "Poggio Berni", "Polesine Parmense", "Porretta Terme", "Pozza di Fassa", "Praso",  "Pratovecchio", "Preore", "Presicce", "Prestine", "Prezzo",
            "Puos d'Alpago", "Quaregna", "Quero", "Quittengo", "Ragoli", "Ramiseto", "Ramponio Verna", "Repubblica Socialista Federale di Jugoslavia", "Revere", "Revò", "Rima San Giuseppe", "Rimasco", "Rio Marina", "Rio nell'Elba",
            "Ripe", "Riva Valdobbia", "Rivignano", "Ro", "Romallo", "Roncone", "Rossano", "Rovagnate", "Ruino", "Sabbia", "Saletto", "Saltara", "San Fedele Intelvi", "San Germano dei Berici", "San Giorgio di Pesaro",  "San Giovanni d'Asso", "San Lorenzo in Banale",
            "San Marcello Pistoiese", "San Nazario", "San Paolo Cervo", "San Piero a Sieve", "Sant'Agostino", "Sant'Angelo in Lizzola", "Sant'Omobono Terme", "Santa Margherita d'Adige", "Sassocorvaro", "Savigno", "Scarperia", "Selve Marcone", "Seppiana", "Serbia e Montenegro",
            "Serra Pedace", "Serrungarina", "Sillano", "Siror", "Sissa", "Smarano", "Solbiate", "Soprana",  "Sorbolo", "Spera", "Spezzano Piccolo", "Stia", "Strigno", "Taio", "Tassullo", "Tavarnelle Val di Pesa", "Teor", "Terlago", "Terres", "Tiarno di Sopra", "Tiarno di Sotto",
            "Tonadico", "Tonengo", "Torriana", "Transacqua", "Trausella", "Trecasali", "Tremenico", "Tremezzo", "Trenta", "Treppo Carnico", "Tres", "Tresigallo", "Trichiana", "Trivero", "Tuenno", "Valda", "Valle Mosso", "Valmala", "Valsecca", "Valstagna", "Valvasone", "Valverde",
            "Varena", "Vas", "Vattaro", "Veddasca", "Vendrogno", "Verderio Inferiore", "Verderio Superiore", "Vergemoli", "Vermezzo", "Veruno", "Vervò", "Vestreno", "Vezzano",  "Vico Canavese", "Viganella", "Vigatto", "Vigo di Fassa", "Vigo Rendena", "Vigolo Vattaro",
            "Villa Agnedo", "Villa Poma", "Villa Rendena", "Villa Vicentina", "Virgilio", "Yemen del Sud", "Zambana", "Zelo Surrigone", "Zibello", "Zoldo Alto", "Zuclo")


        val day1 = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31) //possibili casi
        val day2 = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30)
        val day3 = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29)
        val day4 = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28)

        val mese = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12) //possibili casi del mese

        val risultato = findViewById<TextView>(R.id.textView)
        //anno
        val anno = arrayOf(2023, 2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013, 2012, 2011, 2010, 2009, 2008, 2007, 2006, 2005, 2004, 2003, 2002, 2001,
            2000, 1999, 1998, 1997, 1996, 1995, 1994, 1993, 1992, 1991, 1990, 1989, 1988, 1987, 1986, 1985, 1984, 1983, 1982, 1981, 1980, 1979, 1978, 1977,
            1976, 1975, 1974, 1973, 1972, 1971, 1970, 1969, 1968, 1967, 1966, 1965, 1964, 1963, 1962, 1961, 1960, 1959, 1958, 1957, 1956, 1955, 1954, 1953,
            1952, 1951, 1950, 1949, 1948, 1947, 1946, 1945, 1944, 1943, 1942, 1941, 1940, 1939, 1938, 1937, 1936, 1935, 1934, 1933, 1932, 1931, 1930, 1929,
            1928, 1927, 1926, 1925, 1924, 1923, 1922, 1921, 1920, 1919, 1918, 1917, 1916, 1915, 1914, 1913, 1912, 1911, 1910, 1909, 1908, 1907, 1906, 1905, 1904, 1903, 1902, 1901, 1900) //possibili casi del'anno

        val daySpinner = findViewById<Spinner>(R.id.daySpinner) //Creazione riferimento allo spinner (tendina)
        val monthSpinner = findViewById<Spinner>(R.id.monthSpinner) //Creazione riferimento allo spinner (tendina)
        val yearSpinner = findViewById<Spinner>(R.id.yearSpinner) //Creazione riferimento allo spinner (tendina)

        var meseN: String? = null
        var AnnoN = "0"


        val adapterAnno = ArrayAdapter(this, android.R.layout.simple_spinner_item, anno) //creazione di tutti i possibili casi tramite un array
        yearSpinner.adapter = adapterAnno //.adapter è una proprietà dello spinner che permette di associare l' adapter a yearSpinner
        yearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                //Prendiamo valore di Anno
                AnnoN =  parent.getItemAtPosition(position).toString()
                //creiamo Adapter per il mese con spinner
                val adapterMese = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, mese) //creazione di tutti i possibili casi tramite un array
                monthSpinner.adapter = adapterMese //.adapter è una proprietà dello spinner che permette di associare l' adapter a daySpinner
                monthSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                        //Prendiamo valore del mese
                        meseN =  parent.getItemAtPosition(position).toString()
                        //creiamo adapter per il giorno
                        val adapterGiorno =  when(meseN?.toInt()){
                            1, 3, 5, 7, 8, 10, 12 -> ArrayAdapter(this@MainActivity, android.R.layout.simple_dropdown_item_1line, day1)
                            4, 6, 9, 11 -> ArrayAdapter(this@MainActivity, android.R.layout.simple_dropdown_item_1line, day2)
                            2 -> if(AnnoN.toInt() % 4 == 0 && (AnnoN.toInt() % 100 != 0 || AnnoN.toInt() % 400 == 0))
                            { ArrayAdapter(this@MainActivity, android.R.layout.simple_dropdown_item_1line, day3) }
                            else { ArrayAdapter(this@MainActivity, android.R.layout.simple_dropdown_item_1line, day4) }

                            else -> {ArrayAdapter(this@MainActivity, android.R.layout.simple_dropdown_item_1line, emptyArray())}
                        }
                        daySpinner.adapter = adapterGiorno   //.adapter è una proprietà dello spinner che permette di associare l' adapter a daySpinner
                    }
                    override fun onNothingSelected(parent: AdapterView<*>) {}
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        AnnoN = yearSpinner.selectedItem.toString()

        var giornoN = "0"
        daySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                giornoN = parent.getItemAtPosition(position).toString()

            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // l'istanza del calendario corrente
        val calendar = Calendar.getInstance() //dobbiamo importare la libreria import java.util.Calendar

        //  giorno corrente
        val giornoCorrente = calendar.get(Calendar.DAY_OF_MONTH)

        //  mese corrente (i mesi iniziano da 0, quindi aggiungi 1 per ottenere il valore corretto)
        val meseCorrente = calendar.get(Calendar.MONTH) + 1
        val annoCorrente = calendar.get(Calendar.YEAR)


        //CITTA E SIGLA
        val keysList = ArrayList(cityMap.keys) //array di sole chiavi (città)
        val siglaCittà = findViewById<EditText>(R.id.sigla) //creazione riferimento eT sigla città
        val possibiliCittà = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, keysList) //adapter2 è un array di tutte le possibili città memorizzate
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView) //riferimento all'autoCompleteTextView
        autoCompleteTextView.setAdapter(possibiliCittà) //chiamiamo metodo setAdapter per verificare quello che scriviamo con le città nell'array adapter2

        var sigla_città = ""
        var città = ""
        var Città = ""
        var abolished = ""
        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            città = parent.getItemAtPosition(position).toString() //Se clicchiamo su uno dei suggerimenti che ci darà città prenderà quel valore
            if(città in paesiEU || città in paesiEE || città in cityMap) {
                sigla_città = cityMap[città].toString() //recuperiamo la sigla della città tramite la mappa e accediamo con la chiave(città) associata
                siglaCittà.setText(sigla_città) //settiamo nell'edit text della sigla della città il valore preso dalla mappa
            }
        }


        //Se non clicchiamo il suggerimento chiamiamo il seguente metodo che dopo aver visto che il testo è cambiato prenderà la stringa inserita e la trasforma in stringa assegnandola a città
        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {  //prima e seconda funzione non servono ma sono richieste dall'oggeto textWatcher
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                città = s.toString() //per il colcolo-Codice
                Città = s.toString() //da visualizzare per paesiEU2 visto che l'API li vuole in inglese, li passeremo in inglese ma visualizzeremo l'originale

                if(città in paesiEU || città in paesiEU2){
                    if(città in paesiEU2)
                        città = paesiEU2[città].toString()
                    sigla_città = "EU"
                    siglaCittà.setText(sigla_città)
                }
                else if(paesiEE.contains(città)) {
                    sigla_città = "EE"
                    siglaCittà.setText(sigla_città)
                }
                if(città in Soppresse)
                    abolished = "true"
                else
                    abolished = "false"
            }
        })

        siglaCittà.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                sigla_città = siglaCittà.text.toString()
            }
        })




        val cognome = findViewById<EditText>(R.id.etName1)
        val nome = findViewById<EditText>(R.id.etName2)
        val genere = findViewById<RadioGroup>(R.id.rg)

        val Cognome = cognome.text
        val Nome = nome.text

        // GENERE
        var Genere = ""
        genere.setOnCheckedChangeListener { group, checkedId ->
            // Gestisci l'evento di selezione
            val radioButton: RadioButton = findViewById(checkedId)
            Genere = when (radioButton.id) {
                R.id.rbMale -> "M"
                R.id.rbFemale -> "F"
                else -> ""
            }
        }


        val botton = findViewById<Button>(R.id.button)

        botton.setOnClickListener() {
            if(Nome.isEmpty() || Cognome.isEmpty() || città.isEmpty() ||  sigla_città.isEmpty() || Genere.isEmpty()){
                risultato.text = "Dati mancanti"
            }
            else if ((giornoCorrente < giornoN.toInt() && meseCorrente < meseN?.toInt()!! && AnnoN.toInt() >= annoCorrente) || meseCorrente < meseN?.toInt()!! && AnnoN.toInt() >= annoCorrente)
                risultato.text = "Data non accettata"
            else {
                // Instantiate the RequestQueue.
                val queue = Volley.newRequestQueue(this)
                val url = "https://api.miocodicefiscale.com/calculate?lname=$Cognome&fname=$Nome&gender=$Genere&city=$città&state=$sigla_città&abolished=$abolished&day=$giornoN&month=$meseN&year=$AnnoN&omocodia_level=128&access_token=812b5efa358c63208db2823419bfec59317d99c0e5e13e2beff08aa5acbd71dbd96"
                // Request a string response from the provided URL.
                val stringRequest = StringRequest(
                    Request.Method.GET, url,
                    Response.Listener<String> { response ->
                        val keyWords = '['
                        val endIndex = response.indexOf(keyWords)+1
                        val stringaDaEliminare = response.substring(0, endIndex)

                        val response2 = response.replace(stringaDaEliminare, "")
                        //creeazione array di stringhe con .split
                        val codiciFiscali = response2.split(',')

                        if(codiciFiscali[0] == "{\"status\":false")
                            risultato.text = "Dati non validi"
                        else {
                            risultato.text = "Il codice fiscale richiesto è: ${codiciFiscali[0]}"
                            val scheda = Intent(this, Scheda::class.java)
                            scheda.putExtra("CodiceFiscale", codiciFiscali[0])
                            scheda.putExtra("Cognome", "$Cognome")
                            scheda.putExtra("Nome", "$Nome")
                            scheda.putExtra("Luogo", Città)
                            scheda.putExtra("Provincia", sigla_città)
                            scheda.putExtra("Genere", Genere)
                            scheda.putExtra("Giorno", giornoN)
                            scheda.putExtra("Mese", meseN)
                            scheda.putExtra("Anno", AnnoN)
                            startActivity(scheda)
                        }

                    },
                    Response.ErrorListener { risultato.text = "That didn't work!" })

                // Add the request to the RequestQueue.
                queue.add(stringRequest)
            }
        }
    }
}

