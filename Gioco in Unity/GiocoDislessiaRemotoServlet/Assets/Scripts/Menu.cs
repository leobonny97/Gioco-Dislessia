using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.SceneManagement;
using UnityEngine.UIElements;

public class Menu : MonoBehaviour
{
    private bool istruzioni_perso;
    private bool istruzioni;
    private bool istruzioni_registrazione;
    private bool istruzioni_login;
    private int controllo_username=3;
    private int controllo_password=3;
    private int numero_parole_caricate;
    private int numero_frasi_caricate;
    private bool progressi_da_aggiornare;
    private bool progresso_aggiornato;
    private bool gioco_completato;
    private bool giochiamo;
    public static List<Frase> frasi;
    private List<Distrattore> distrattori;
    private List<Parole> parole;
    public GUIStyle stilebottoni;
    public GUIStyle stiletesto;
    public GUIStyle stileistruzioni1;
    public GUIStyle stileistruzioni2;
    private bool loginFallito;
    private bool usernameEsistente;
    private bool menu;
    private bool registrazione;
    private bool login;
    static public Giocatore giocatore;
    private bool giocatoreOn;
    private string nome;
    private string password;

    // Start is called before the first frame update
    void Start()
    {
        numero_parole_caricate = 0;
        numero_frasi_caricate = 0;
        progressi_da_aggiornare = true;
        progresso_aggiornato = false;
        gioco_completato = false;
        giochiamo = false;
        loginFallito = false;
        usernameEsistente = false;
        menu = true;
        registrazione = false;
        login = false;
        giocatoreOn = false;
        nome = "username";
        password = "password";
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnGUI()
    {
        if (GestioneCollisione.perso)
        {
            if(istruzioni_perso)
            {
                GUILayout.BeginArea(new Rect(Screen.width / 2 - 250, Screen.height / 2 - 150, 500, 500));
                GUILayout.Label("Obiettivo", stileistruzioni1);
                GUILayout.Label("Seleziona le parole visualizzate in alto", stileistruzioni2);
                GUILayout.Label("Comandi", stileistruzioni1);
                GUILayout.Label("Trascina verso sinistra: sposta a sinistra", stileistruzioni2);
                GUILayout.Label("Trascina verso destra: sposta a destra", stileistruzioni2);
                if (GUILayout.Button("Torna al menu", stilebottoni))
                {
                    istruzioni_perso = false;
                }
                GUILayout.EndArea();
            }
            else
            {
                GUILayout.BeginArea(new Rect(Screen.width / 2 - 250, Screen.height / 2 - 150, 500, 500));
                GUILayout.Label("Hai perso", stiletesto);
                if (GUILayout.Button("Riprova", stilebottoni))
                {
                    SceneManager.LoadScene("SampleScene");
                }
                if (GUILayout.Button("Istruzioni", stilebottoni))
                {
                    istruzioni_perso = true;
                }
                GUILayout.EndArea();
            }
        }
        else if(GestioneCollisione.vinto)
        {
            GUILayout.BeginArea(new Rect(Screen.width / 2 - 250, Screen.height / 2 - 150, 500, 500));
            GUILayout.Label("Hai vinto", stiletesto);
            if (frasi.Count!=0)
            {
                if (GUILayout.Button("Continua", stilebottoni))
                {
                    SceneManager.LoadScene("SampleScene");
                }
            }
            else
            {
                if(progressi_da_aggiornare)
                {
                    progressi_da_aggiornare = false;
                    updateProgresso(giocatore.getProgresso() + 1, giocatore.getUsername());
                }
                if(progresso_aggiornato)
                {
                    if (GUILayout.Button("Torna al quiz", stilebottoni))
                    {
                        Application.Quit();
                    }
                }
            }
            GUILayout.EndArea();
        }
        else
        {
            if (!giocatoreOn)
            {
                if (menu)
                {
                    GUILayout.BeginArea(new Rect(Screen.width / 2 - 250, Screen.height / 2 - 150, 500, 500));
                    if (GUILayout.Button("Registrati", stilebottoni))
                    {
                        menu = false;
                        registrazione = true;
                    }
                    if (GUILayout.Button("Login e gioca", stilebottoni))
                    {
                        menu = false;
                        login = true;
                    }
                    if (GUILayout.Button("Istruzioni", stilebottoni))
                    {
                        menu = false;
                        istruzioni = true;
                    }
                    if (GUILayout.Button("Esci", stilebottoni))
                    {
                        Application.Quit();
                    }
                    GUILayout.EndArea();
                }
                if (registrazione)
                {
                    GUILayout.BeginArea(new Rect(Screen.width / 2 - 250, Screen.height / 2 - 150, 500, 500));
                    nome = GUILayout.TextField(nome, GUI.skin.textField.fontSize = 60);
                    password = GUILayout.TextField(password, GUI.skin.textField.fontSize = 60);
                    if (GUILayout.Button("Registrati", stilebottoni))
                    {
                        controllo_username = controlloUsername(nome);
                        controllo_password = controlloPassword(password);
                        if(controllo_username==1 && controllo_password==1)
                        {
                            addGiocatore(nome, password);
                        }
                    }
                    if(controllo_username==-1)
                    {
                        GUILayout.Label("L'username deve contenere almeno 8 caratteri", stiletesto);
                    }
                    else if (controllo_username == -2)
                    {
                        GUILayout.Label("Username non consentita", stiletesto);
                    }
                    else if (controllo_username == 0)
                    {
                        GUILayout.Label("L'username deve contenere al più 16 caratteri", stiletesto);
                    }
                    else if (controllo_password == -1)
                    {
                        GUILayout.Label("La password deve contenere almeno 8 caratteri", stiletesto);
                    }
                    else if (controllo_password == -2)
                    {
                        GUILayout.Label("Password non consentita", stiletesto);
                    }
                    else if (controllo_password == 0)
                    {
                        GUILayout.Label("La password deve contenere al più 16 caratteri", stiletesto);
                    }
                    if (usernameEsistente)
                    {
                        GUILayout.Label("Username già esistente", stiletesto);
                    }
                    if (GUILayout.Button("Torna indietro", stilebottoni))
                    {
                        menu = true;
                        registrazione = false;
                    }
                    GUILayout.EndArea();
                }
                if (login)
                {
                    GUILayout.BeginArea(new Rect(Screen.width / 2 - 250, Screen.height / 2 - 150, 500, 500));
                    nome = GUILayout.TextField(nome, GUI.skin.textField.fontSize = 60);
                    password = GUILayout.TextField(password, GUI.skin.textField.fontSize = 60);
                    if (GUILayout.Button("Login", stilebottoni))
                    {
                        loginGiocatore(nome, password);
                    }
                    if (loginFallito)
                    {
                        GUILayout.Label("Username o password errati", stiletesto);
                    }
                    if (GUILayout.Button("Torna indietro", stilebottoni))
                    {
                        menu = true;
                        login = false;
                    }
                    GUILayout.EndArea();
                }
                if(istruzioni)
                {
                    GUILayout.BeginArea(new Rect(Screen.width / 2 - 250, Screen.height / 2 - 150, 500, 500));
                    GUILayout.Label("Obiettivo", stileistruzioni1);
                    GUILayout.Label("Seleziona le parole visualizzate in alto", stileistruzioni2);
                    GUILayout.Label("Comandi", stileistruzioni1);
                    GUILayout.Label("Trascina verso sinistra: sposta a sinistra", stileistruzioni2);
                    GUILayout.Label("Trascina verso destra: sposta a destra", stileistruzioni2);
                    if (GUILayout.Button("Torna al menu", stilebottoni))
                    {
                        menu = true;
                        istruzioni = false;
                    }
                    GUILayout.EndArea();
                }
            }
            else
            {
                if(istruzioni_registrazione)
                {
                    GUILayout.BeginArea(new Rect(Screen.width / 2 - 250, Screen.height / 2 - 150, 500, 500));
                    GUILayout.Label("Obiettivo", stileistruzioni1);
                    GUILayout.Label("Seleziona le parole visualizzate in alto", stileistruzioni2);
                    GUILayout.Label("Comandi", stileistruzioni1);
                    GUILayout.Label("Trascina verso sinistra: sposta a sinistra", stileistruzioni2);
                    GUILayout.Label("Trascina verso destra: sposta a destra", stileistruzioni2);
                    if (GUILayout.Button("Torna indietro", stilebottoni))
                    {
                        istruzioni_registrazione = false;
                    }
                    GUILayout.EndArea();
                }
                else if(istruzioni_login)
                {
                    GUILayout.BeginArea(new Rect(Screen.width / 2 - 250, Screen.height / 2 - 150, 500, 500));
                    GUILayout.Label("Obiettivo", stileistruzioni1);
                    GUILayout.Label("Seleziona le parole visualizzate in alto", stileistruzioni2);
                    GUILayout.Label("Comandi", stileistruzioni1);
                    GUILayout.Label("Trascina verso sinistra: sposta a sinistra", stileistruzioni2);
                    GUILayout.Label("Trascina verso destra: sposta a destra", stileistruzioni2);
                    if (GUILayout.Button("Torna indietro", stilebottoni))
                    {
                        istruzioni_login = false;
                    }
                    GUILayout.EndArea();
                }
                else if (registrazione)
                {
                    GUILayout.BeginArea(new Rect(Screen.width / 2 - 250, Screen.height / 2 - 150, 500, 500));
                    GUILayout.Label("Registrazione effettuata con successo", stiletesto);
                    if(giochiamo)
                    {
                        if (GUILayout.Button("Istruzioni", stilebottoni))
                        {
                            istruzioni_registrazione = true;
                        }
                        if (GUILayout.Button("Gioca", stilebottoni))
                        {
                            registrazione = false;
                            SceneManager.LoadScene("SampleScene");
                        }
                    }
                    GUILayout.EndArea();
                }
                else if (login)
                {
                    GUILayout.BeginArea(new Rect(Screen.width / 2 - 250, Screen.height / 2 - 150, 500, 500));
                    GUILayout.Label("Login effettuato con successo", stiletesto);
                    if (gioco_completato)
                    {
                        GUILayout.Label("Gioco già completato", stiletesto);
                        GUILayout.Button("Esci", stilebottoni);
                    }
                    else if (giochiamo)
                    {
                        if (GUILayout.Button("Istruzioni", stilebottoni))
                        {
                            istruzioni_login = true;
                        }
                        if (GUILayout.Button("Gioca", stilebottoni))
                        {
                            login = false;
                            SceneManager.LoadScene("SampleScene");
                        }
                    }
                    GUILayout.EndArea();
                }
                else
                {
                    GUILayout.BeginArea(new Rect(Screen.width / 2 - 250, Screen.height / 2 - 150, 500, 500));
                    if (GUILayout.Button("Gioca", stilebottoni))
                    {
                        SceneManager.LoadScene("SampleScene");
                    }
                    GUILayout.EndArea();
                }
            }
        }
    }

    public void addGiocatore(string username, string password)
    {
        StartCoroutine(Register(username, password));
    }

    IEnumerator Register(string username, string password)
    {
        WWWForm form = new WWWForm();
        form.AddField("username", username);
        form.AddField("password", password);
        UnityWebRequest www = UnityWebRequest.Post("http://localhost:8080/GiocoDislessia/Registrazione", form);
        yield return www.SendWebRequest();
        if (www.downloadHandler.text == "1")
        {
            Debug.Log("Registrazione riuscita");
            usernameEsistente = false;
            giocatore = new Giocatore(nome, password, 1);
            giocatoreOn = true;
            getFrasi(giocatore.getProgresso());
        }
        else if (www.downloadHandler.text == "0")
        {
            Debug.Log("Connessione non riuscita");
        }
        else if(www.downloadHandler.text == "2")
        {
            Debug.Log("Username esistente");
            usernameEsistente = true;
        }
    }

    public void loginGiocatore(string username, string password)
    {
        StartCoroutine(Login(username, password));
    }

    IEnumerator Login(string username, string password)
    {
        WWWForm form = new WWWForm();
        form.AddField("username", username);
        form.AddField("password", password);
        UnityWebRequest www = UnityWebRequest.Post("http://localhost:8080/GiocoDislessia/Login", form);
        yield return www.SendWebRequest();
        if (www.downloadHandler.text == "0")
        {
            Debug.Log("Login fallito");
            loginFallito = true;
        }
        else if (www.downloadHandler.text == "-1")
        {
            Debug.Log("Connessione non riuscita");
        }
        else
        {
            Debug.Log("Login effettuato");
            loginFallito = false;
            Debug.Log("" + www.downloadHandler.text);
            giocatore = new Giocatore(username, password, int.Parse(www.downloadHandler.text));
            giocatoreOn = true;
            getFrasi(giocatore.getProgresso());
        }
    }

    public void getFrasi(int livello)
    {
        StartCoroutine(caricaLivello(livello));
    }

    IEnumerator caricaLivello(int livello)
    {
        WWWForm form = new WWWForm();
        form.AddField("livello", livello);
        UnityWebRequest www = UnityWebRequest.Post("http://localhost:8080/GiocoDislessia/CaricaLivello", form);
        yield return www.SendWebRequest();
        if (www.downloadHandler.text == "-1")
        {
            Debug.Log("Connessione non riuscita");
        }
        else if (www.downloadHandler.text == "-2")
        {
            gioco_completato = true;
            Debug.Log("Nessuna frase trovata");
        }
        else
        {
            Debug.Log("Carico le frasi");
            frasi = new List<Frase>();
            string[] result = www.downloadHandler.text.Split('\t');
            foreach (string s in result)
            {
                if (s != null && s != "")
                {
                    Debug.Log("Frase " + s);
                    frasi.Add(new Frase(int.Parse(s)));
                }
            }
            int c = 0;
            Debug.Log("Sono state caricate " + frasi.Count + " frasi.");
            foreach (Frase frase in frasi)
            {
                getParole(frase.getId(), c);
                numero_frasi_caricate++;
                c++;
            }
        }
    }

    public void getParole(int frase, int c)
    {
        StartCoroutine(caricaParole(frase, c));
    }

    IEnumerator caricaParole(int frase, int c)
    {
        WWWForm form = new WWWForm();
        form.AddField("frase", frase);
        UnityWebRequest www = UnityWebRequest.Post("http://localhost:8080/GiocoDislessia/CaricaParole", form);
        yield return www.SendWebRequest();
        if (www.downloadHandler.text == "-1")
        {
            Debug.Log("Connessione non riuscita");
        }
        else if (www.downloadHandler.text == "-2")
        {
            Debug.Log("Nessuna parola trovata");
        }
        else
        {
            Debug.Log("Carico le parole");
            string[] result = www.downloadHandler.text.Split('\t');
            parole = new List<Parole>();
            int temp;
            for(temp=0;temp<result.Length-1;temp=temp+4)
            {
                if(frasi[numero_frasi_caricate-1].getId()==frase)
                {
                    numero_parole_caricate++;
                }
                Debug.Log("Parola id: " + result[temp + 1] + ", stringa: " + result[temp + 3] + ", frase id: " + result[temp] + ", ordine: " + result[temp + 2]);
                parole.Add(new Parole(int.Parse(result[temp + 1]), result[temp + 3], int.Parse(result[temp]), int.Parse(result[temp + 2])));
            }
            int c2 = 0;
            foreach (Parole parola in parole)
            {
                getDistrattori(parola.getId(), c2, c);
                c2++;
            }
            frasi[c].setParole(parole);
        }
    }

    public void getDistrattori(int parola, int posizione, int frase)
    {
        StartCoroutine(caricaDistrattori(parola, posizione, frase));
    }

    IEnumerator caricaDistrattori(int parola, int posizione, int frase)
    {
        WWWForm form = new WWWForm();
        form.AddField("parola", parola);
        UnityWebRequest www = UnityWebRequest.Post("http://localhost:8080/GiocoDislessia/CaricaDistrattori", form);
        yield return www.SendWebRequest();
        if (www.downloadHandler.text == "-1")
        {
            Debug.Log("Connessione non riuscita");
        }
        else if (www.downloadHandler.text == "-2")
        {
            Debug.Log("Nessuna distrattore trovato");
        }
        else
        {
            Debug.Log("Carico i distrattori");
            string[] result = www.downloadHandler.text.Split('\t');
            distrattori = new List<Distrattore>();
            int temp;
            for (temp = 0; temp < result.Length - 1; temp = temp + 2)
            {
                Debug.Log("Distrattore id: " + result[temp] + ", stringa: " + result[temp + 1]);
                distrattori.Add(new Distrattore(int.Parse(result[temp]), result[temp + 1]));
            }
            frasi[frase].getParole()[posizione].setDistrattore(distrattori);
        }
        if (parola == frasi[numero_frasi_caricate - 1].getParole()[numero_parole_caricate - 1].getId())
        {
            Debug.Log("Adesso puoi giocare");
            giochiamo = true;
        }
    }

    public void updateProgresso(int progresso, string username)
    {
        StartCoroutine(aggiornaProgresso(progresso, username));
    }

    IEnumerator aggiornaProgresso(int progresso, string username)
    {
        WWWForm form = new WWWForm();
        form.AddField("progresso", progresso);
        form.AddField("username", username);
        UnityWebRequest www = UnityWebRequest.Post("http://localhost:8080/GiocoDislessia/AggiornaProgresso", form);
        yield return www.SendWebRequest();
        if (www.downloadHandler.text == "0")
        {
            Debug.Log("Progresso aggiornato");
            progresso_aggiornato = true;
        }
        else if (www.downloadHandler.text == "1")
        {
            Debug.Log("Connessione non riuscita");
            SceneManager.LoadScene("Menu");
        }
    }

    public int controlloUsername(string nome)
    {
        if(nome.Length<8)
        {
            Debug.Log("Username troppo corta");
            return -1;
        }
        else if(nome.Length>16)
        {
            Debug.Log("Username troppo lunga");
            return 0;
        }
        else if(nome=="username")
        {
            Debug.Log("Non è possibile utilizzare tale username");
            return -2;
        }
        else
        {
            return 1;
        }
    }

    public int controlloPassword(string password)
    {
        if (password.Length < 8)
        {
            Debug.Log("Password troppo corta");
            return -1;
        }
        else if (password.Length > 16)
        {
            Debug.Log("Password troppo lunga");
            return 0;
        }
        else if (password == "password")
        {
            Debug.Log("Non è possibile utilizzare tale password");
            return -2;
        }
        else
        {
            return 1;
        }
    }
}