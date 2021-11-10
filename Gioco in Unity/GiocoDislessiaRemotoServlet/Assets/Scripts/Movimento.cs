using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Movimento : MonoBehaviour
{
    public Animator anim;
    private float time;


    // Start is called before the first frame update
    void Start()
    {
        anim = this.GetComponent<Animator>();
        time = 0.0f;
    }

    // Update is called once per frame
    void Update()
    {
        this.transform.Translate(Vector3.forward * 1.2f * Time.deltaTime);
        time -= Time.deltaTime;
        anim.SetBool("Run", true);
        this.transform.Translate(Vector3.forward * 12.5f * Time.deltaTime);
    }


}
