using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GestioneTouch : MonoBehaviour
{
    Vector2 startPos, endPos;

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        Vector3 vector2 = transform.localPosition;
        if (Input.touchCount > 0)
        {
            Touch touch = Input.GetTouch(0);
            switch (touch.phase)
            {
                //When a touch has first been detected, change the message and record the starting position
                case TouchPhase.Began:
                    // Record initial touch position.
                    Debug.Log("Inizio tocco");
                    startPos = touch.position;
                    break;

                //Determine if the touch is a moving touch
                case TouchPhase.Moved:
                    // Determine direction by comparing the current touch position with the initial one
                    Debug.Log("Mi sto muovendo");
                    break;

                case TouchPhase.Ended:
                    // Report that the touch has ended when it ends
                    Debug.Log("Fine tocco");
                    endPos = touch.position;
                    if (startPos.x - endPos.x > 0f)
                    {
                        if(transform.position.x == 0f)
                        {
                            Debug.Log("Startpos. " + startPos);
                            Debug.Log("Endpos" + endPos);
                            transform.position = new Vector3(-10f, transform.position.y, transform.position.z);
                        }
                        else if(transform.position.x == 10f)
                        {
                            Debug.Log("Startpos. " + startPos);
                            Debug.Log("Endpos" + endPos);
                            transform.position = new Vector3(0f, transform.position.y, transform.position.z);
                        }
                    }
                    else if (startPos.x - endPos.x < 0f)
                    {
                        if(transform.position.x == 0f)
                        {
                            Debug.Log("Startpos. " + startPos);
                            Debug.Log("Endpos" + endPos);
                            transform.position = new Vector3(10f, transform.position.y, transform.position.z);
                        }
                        else if(transform.position.x == -10f)
                        {
                            Debug.Log("Startpos. " + startPos);
                            Debug.Log("Endpos" + endPos);
                            transform.position = new Vector3(0f, transform.position.y, transform.position.z);
                        }
                    }
                    break;
            }
        }
    }
}

