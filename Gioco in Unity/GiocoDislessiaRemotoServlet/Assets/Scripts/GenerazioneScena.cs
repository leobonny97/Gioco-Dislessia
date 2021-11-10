using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;
using UnityEngine.SceneManagement;
using UnityEngine.Networking;

public class GenerazioneScena : MonoBehaviour
{
    public static Frase frase_corrente;
    public static int contatore_parole;
    public GUIStyle stilebottoni;
    public GameObject ob;
    private Giocatore giocatore;
    private int posizione_pavimento;
    private int posizione_personaggio;
    private int posizione_parole;
    private bool frase_impostata;
    public static int n;

    // Start is called before the first frame update
    void Start()
    {
        contatore_parole = 0;
        frase_impostata = false;
        posizione_personaggio = 77;
        posizione_pavimento = 150;
        posizione_parole = -10;

        if (Menu.frasi.Count > 0)
        {
            Debug.Log(Menu.frasi.Count);
            n = Random.Range(0, Menu.frasi.Count);
            frase_corrente = Menu.frasi[n];
            frase_impostata = true;
            List<Parole> parole = frase_corrente.getParole();
            foreach (Parole parola in parole)
            {
                TextMeshPro text = ob.GetComponent<TextMeshPro>();
                text.text = parola.getParola();
                if (parola.getDistrattori() != null)
                {
                    int n2 = Random.Range(0, 3);
                    if (n2 == 0)
                    {
                        Instantiate(ob, new Vector3(10f, 3.5f, posizione_parole), new Quaternion());
                        n2 = Random.Range(0, 2);
                        List<Distrattore> distrattori = parola.getDistrattori();
                        if (n2 == 0)
                        {
                            text.text = distrattori[0].getParola();
                            Instantiate(ob, new Vector3(0f, 3.5f, posizione_parole), new Quaternion());
                            text.text = distrattori[1].getParola();
                            Instantiate(ob, new Vector3(-10f, 3.5f, posizione_parole), new Quaternion());
                        }
                        else
                        {
                            text.text = distrattori[0].getParola();
                            Instantiate(ob, new Vector3(-10f, 3.5f, posizione_parole), new Quaternion());
                            text.text = distrattori[1].getParola();
                            Instantiate(ob, new Vector3(0f, 3.5f, posizione_parole), new Quaternion());
                        }
                    }
                    else if (n2 == 1)
                    {
                        Instantiate(ob, new Vector3(0f, 3.5f, posizione_parole), new Quaternion());
                        n2 = Random.Range(0, 2);
                        List<Distrattore> distrattori = parola.getDistrattori();
                        if (n2 == 0)
                        {
                            text.text = distrattori[0].getParola();
                            Instantiate(ob, new Vector3(10f, 3.5f, posizione_parole), new Quaternion());
                            text.text = distrattori[1].getParola();
                            Instantiate(ob, new Vector3(-10f, 3.5f, posizione_parole), new Quaternion());
                        }
                        else
                        {
                            text.text = distrattori[0].getParola();
                            Instantiate(ob, new Vector3(-10f, 3.5f, posizione_parole), new Quaternion());
                            text.text = distrattori[1].getParola();
                            Instantiate(ob, new Vector3(10f, 3.5f, posizione_parole), new Quaternion());
                        }
                    }
                    else
                    {
                        Instantiate(ob, new Vector3(-10f, 3.5f, posizione_parole), new Quaternion());
                        n2 = Random.Range(0, 2);
                        List<Distrattore> distrattori = parola.getDistrattori();
                        if (n2 == 0)
                        {
                            text.text = distrattori[0].getParola();
                            Instantiate(ob, new Vector3(0f, 3.5f, posizione_parole), new Quaternion());
                            text.text = distrattori[1].getParola();
                            Instantiate(ob, new Vector3(10f, 3.5f, posizione_parole), new Quaternion());
                        }
                        else
                        {
                            text.text = distrattori[0].getParola();
                            Instantiate(ob, new Vector3(10f, 3.5f, posizione_parole), new Quaternion());
                            text.text = distrattori[1].getParola();
                            Instantiate(ob, new Vector3(0f, 3.5f, posizione_parole), new Quaternion());
                        }
                    }
                }
                else
                {
                    Instantiate(ob, new Vector3(10f, 3.5f, posizione_parole), new Quaternion());
                    Instantiate(ob, new Vector3(0f, 3.5f, posizione_parole), new Quaternion());
                    Instantiate(ob, new Vector3(-10f, 3.5f, posizione_parole), new Quaternion());
                }
                posizione_parole = posizione_parole + 50;
            }
        }
    }

    // Update is called once per frame
    void Update()
    {
        if (transform.localPosition.z > posizione_personaggio)
        {
            posizione_pavimento = posizione_pavimento + 150;
            posizione_personaggio = posizione_personaggio + 150;
            GameObject pavimento = Resources.Load<GameObject>("Pavimento");
            pavimento.transform.position = new Vector3(0f, 0f, posizione_pavimento);
            GameObject nuovoPavimento = (GameObject)Instantiate(pavimento);
        }
        if (contatore_parole == frase_corrente.getParole().Count)
        {
            Debug.Log("Hai vinto");
            GestioneCollisione.vinto = true;
            Menu.frasi.RemoveAt(n);
            SceneManager.LoadScene("Menu");
        }
    }

    private void Awake()
    {
        GameObject pavimento = Resources.Load<GameObject>("Pavimento");
        pavimento.transform.position = new Vector3(0f, 0f, 150f);
        GameObject nuovoPavimento = (GameObject)Instantiate(pavimento);
        giocatore = Menu.giocatore;
    }

    private void OnGUI()
    {
        if(frase_impostata)
        {
            GUILayout.BeginArea(new Rect(Screen.width / 2 - 250, Screen.height / 2 - 150, 500, 500));
            if(contatore_parole < frase_corrente.getParole().Count)
            {
                GUILayout.Button(frase_corrente.getParole()[contatore_parole].getParola(), stilebottoni);
            }
            GUILayout.EndArea();
        }
    }


    




}

