# LeedsTechnicalTest

#Problem --Best Available:
A small theatre venue needs an application to assign the best available seat to customers. 
The purpose of this problem is to find the best seat in the venue, as quickly and efficiently as possible. Customers may be adults, children (who are accompanied by at least one adult), or may have accessibility requirements such as wheel-chair friendly seating (always accompanied by at least one companion).
The “best” seats are the ones closest to the stage. Seats must not be double booked, and empty seats should be avoided. Wheelchair companions must be seated next to the wheelchair friendly seat. Group bookings should not span the isle, where possible.
Test input:
{
    "requests": [
        {
            "id": 1,
            "seats": [
                {
                    "seat": 1,
                    "type": "wheelchair"
                },
                {
                    "seat": 1,
                    "type": "companion"
                }
            ]
        },
        {
            "id": 2,
            "seats": [
                {
                    "seat": 2,
                    "type": "adult"
                },
                {
                    "seat": 1,
                    "type": "child"
                }
            ]
        },
        {
            "id": 3,
            "seats": [
                {
                    "seat": 6,
                    "type": "adult"
                }
            ]
        },
        {
            "id": 4,
            "seats": [
                {
                    "seat": 7,
                    "type": "child"
                },
                {
                    "seat": 1,
                    "type": "adult"
                }
            ]
        },
        {
            "id": 5,
            "seats": [
                {
                    "seat": 2,
                    "type": "adult"
                }
            ]
        },
        {
            "id": 6,
            "seats": [
                {
                    "seat": 3,
                    "type": "adult"
                }
            ]
        },
        {
            "id": 7,
            "seats": [
                {
                    "seat": 4,
                    "type": "adult"
                }
            ]
        },
        {
            "id": 8,
            "seats": [
                {
                    "seat": 1,
                    "type": "wheelchair"
                },
                {
                    "seat": 2,
                    "type": "companion"
                }
            ]
        }
    ]
}

Venue layout:
Stage
A1	A2	A3	
Isle	A4	A5	A6
B1	B2	B3 (w)		B4 (w)	B5	B6
C1	C2	C3		C4	C5	C6
D1	D2	D3		D4	D5	D6
Seats B3 and B4 are wheelchair-friendly seats.
Sample output:
<Seat ID>:<Request ID>-<Seat type>

A1:R2-adult
A2:R2-adult
B1:R8-companion
B2:R8-companion
B3:R8-wheelchair





#Design Solution:

We will be looking at a number of things including the design aspect of your solution and your object oriented programming skills. Whilst these are small problems, we expect you to submit what you believe is “production-quality” code that you would be able to run, maintain and evolve. We prefer code submissions that exhibit loose coupling, a high degree of encapsulation with cyclomatic complexity distributed relatively evenly. A quick `README` explaining how to run your program will no doubt help.


1.Blocked the Seats for Wheelchair along with Companion.
2.Allocated the seats on requests (first come first serve)
3.The theater layout is made up of 1 or more rows.  Each row is made up of 1 or more sections separated by a space.
4.Run the project according to the input from bookingRequest.json .





