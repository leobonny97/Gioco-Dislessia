using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MovimentoCamera : MonoBehaviour
{
    private Vector3 offset;
    public GameObject obj;

    // Start is called before the first frame update
    void Start()
    {
        offset = transform.position - obj.transform.position;
    }

    // Update is called once per frame
    void Update()
    {
        transform.position = offset + obj.transform.position;
    }
}
