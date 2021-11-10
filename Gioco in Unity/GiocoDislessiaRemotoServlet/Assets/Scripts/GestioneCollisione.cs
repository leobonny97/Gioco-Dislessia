using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.SceneManagement;

public class GestioneCollisione : MonoBehaviour
{
    public static bool perso;
    public static bool vinto;

    // Start is called before the first frame update
    void Start()
    {
        perso = false;
        vinto = false;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnTriggerEnter(Collider other)
    {
        Debug.Log("Collisione");
        TextMeshPro text = other.gameObject.GetComponent<TextMeshPro>();
        if(GenerazioneScena.frase_corrente.getParole()[GenerazioneScena.contatore_parole].getParola()==text.text)
        {
            Debug.Log("Bravo");
            GenerazioneScena.contatore_parole++;
        }
        else
        {
            int id_distrattore;
            Debug.Log("Hai perso");
            perso = true;
            List<Distrattore> distrattori = GenerazioneScena.frase_corrente.getParole()[GenerazioneScena.contatore_parole].getDistrattori();
            foreach(Distrattore distrattore in distrattori)
            {
                if(distrattore.cercaDistrattore(text.text))
                {
                    id_distrattore = distrattore.getId();
                    addErrore(GenerazioneScena.frase_corrente.getId(), GenerazioneScena.frase_corrente.getParole()[GenerazioneScena.contatore_parole].getId(),id_distrattore, Menu.giocatore.getUsername());
                }
            }
        }
        Destroy(other.gameObject);
    }

    public void addErrore(int frase, int parola, int distrattore, string giocatore)
    {
        StartCoroutine(caricaErrore(frase, parola, distrattore, giocatore));
    }

    IEnumerator caricaErrore(int frase, int parola, int distrattore, string giocatore)
    {
        WWWForm form = new WWWForm();
        form.AddField("frase", frase);
        form.AddField("parola", parola);
        form.AddField("distrattore", distrattore);
        form.AddField("giocatore", giocatore);
        UnityWebRequest www = UnityWebRequest.Post("http://localhost:8080/GiocoDislessia/AddErrore", form);
        yield return www.SendWebRequest();
        if (www.downloadHandler.text == "0")
        {
            Debug.Log("Errore caricato");
            SceneManager.LoadScene("Menu");
        }
        else if (www.downloadHandler.text == "1")
        {
            Debug.Log("Connessione non riuscita");
            SceneManager.LoadScene("Menu");
        }
    }

}
