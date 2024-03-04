#include "UI.h"
#include <iostream>
#include <string>

using namespace UI;
using namespace std;

Electric_scooter_UI::Electric_scooter_UI() {

    scooter_controller = make_shared<Electric_scooter_Controller>();
}

void Electric_scooter_UI::RUN() {

    char choice;

    Type();
    cout << "Choice:\n";
    cin >> choice;

    switch(choice){

        case '1':
            scooter_controller->InMemory();
            scooter_controller->add_InMemory();
            break;
        case '2':
            scooter_controller->CSV();
            scooter_controller->add_CSV();
            break;
        case '0':
            break;
        default:
            cout<<"Choice does not exist.\n";

    }

    do{

        Menu();
        cout << "Choice:\n";
        cin >> choice;

        switch(choice){

            case '1':
                Manager();
                break;
            case '2':
                User();
                break;
            case '0':
                break;
            default:
                cout<<"Choice does not exist.\n";

        }

    }while(choice != '0');

}

void Electric_scooter_UI::Menu() {

    cout<<"Who uses the program?\n";
    cout<<"1. Manager\n";
    cout<<"2. User\n";
    cout<<"0. Stop program\n";

}

void Electric_scooter_UI::Type() {

    cout<<"Type?\n";
    cout<<"1. Volatile\n";
    cout<<"2. Persistent\n";
    cout<<"0. Stop program\n";
}

void Electric_scooter_UI::Manager_menu() {

    cout<<"Option?\n";
    cout<<"1. Add\n";
    cout<<"2. Delete\n";
    cout<<"3. Edit\n";
    cout<<"4. Search by location\n";
    cout<<"5. Filter by commissioning date\n";
    cout<<"6. Filter by kilometers\n";
    cout<<"7. Sort by date\n";
    cout<<"0. Stop manager use\n";

}

void Electric_scooter_UI::User_menu() {

    cout<<"Option?\n";
    cout<<"1. Search by location\n";
    cout<<"2. Filter by commissioning date\n";
    cout<<"3. Filter by kilometers\n";
    cout<<"4. Modify condition to in-use\n";
    cout<<"5. Reserve scooter\n";
    cout<<"6. See reserved scooters\n";
    cout<<"0. Stop user use\n";

}


void Electric_scooter_UI::Manager() {

    char choice;

    do{

        Manager_menu();
        cout << "Choice:\n";
        cin >> choice;

        switch(choice){

            case '1':
                Add();
                break;
            case '2':
                Delete();
                break;
            case '3':
                Edit();
                break;
            case '4':
                Search_by_location();
                break;
            case '5':
                Filter_by_commissioning_date();
                break;
            case '6':
                Filter_by_kilometer();
                break;
            case '7':
                Sort_by_commissioning_date();
                break;
            case '0':
                break;
            default:
                cout<<"Choice does not exist.\n";

        }

    }while(choice != '0');

}

void Electric_scooter_UI::User() {

    char choice;

    do{

        User_menu();
        cout << "Choice:\n";
        cin >> choice;

        switch(choice){

            case '1':
                Search_by_location();
                break;
            case '2':
                Filter_by_commissioning_date();
                break;
            case '3':
                Filter_by_kilometer();
                break;
            case '4':
                Modify_condition();
                break;
            case '5':
                Reserve_scooter();
                break;
            case '6':
                See_reserved_scooters();
                break;
            case '0':
                break;
            default:
                cout<<"Choice does not exist.\n";

        }

    }while(choice != '0');

}

void Electric_scooter_UI::Add(){

    string scooter_identifier;
    string scooter_model;
    string scooter_commissioning_date;
    int scooter_kilometer;
    string scooter_location;
    string scooter_condition;

    cout<<"Identifier? (Format: LLL, where L = letter)\n";
    cin>>scooter_identifier;

    if(scooter_controller->exists(scooter_identifier))
        cout<<"Scooter already exists.\n";
    else {

        cout << "Model?\n";
        cin >> scooter_model;

        cout << "Commissioning date? (Format: YYYYMMDD, where YYYY = year, MM = month, DD = day)\n";
        cin >> scooter_commissioning_date;

        cout << "Kilometers?\n";
        cin >> scooter_kilometer;

        cout << "Location?\n";
        cin >> scooter_location;

        cout << "Condition? (in-use/ reserved/ waiting/ out-of-use/ parked)\n";
        cin >> scooter_condition;

        scooter_controller->add(scooter_identifier, scooter_model, scooter_commissioning_date, scooter_kilometer,
                                scooter_location, scooter_condition);
    }
}

void Electric_scooter_UI::Delete() {

    string scooter_identifier;

    cout<<"Identifier? (Format: LLL, where L = letter)\n";
    cin>>scooter_identifier;

    if(!scooter_controller->exists(scooter_identifier))
        cout<<"Scooter does not exist.\n";
    else
        scooter_controller->del(scooter_identifier);

}

void Electric_scooter_UI::Edit() {

    string scooter_identifier;
    string scooter_model;
    string scooter_commissioning_date;
    int scooter_kilometer;
    string scooter_location;
    string scooter_condition;

    cout<<"Identifier? (Format: LLL, where L = letter)\n";
    cin>>scooter_identifier;

    if(!scooter_controller->exists(scooter_identifier))
        cout<<"Scooter does not exist.\n";
    else {

        cout << "Model?\n";
        cin >> scooter_model;

        cout << "Commissioning date? (Format: YYYYMMDD, where YYYY = year, MM = month, DD = day)\n";
        cin >> scooter_commissioning_date;

        cout << "Kilometers?\n";
        cin >> scooter_kilometer;

        cout << "Location?\n";
        cin >> scooter_location;

        cout << "Condition? (in-use/ reserved/ waiting/ out-of-use/ parked)\n";
        cin >> scooter_condition;

        scooter_controller->edit(scooter_identifier, scooter_model, scooter_commissioning_date, scooter_kilometer,
                                 scooter_location, scooter_condition);
    }
}

void Electric_scooter_UI::Search_by_location() {

    string scooter_location;
    vector <Electric_scooter> scooters;

    cout<<"Location?\n";
    cin>>scooter_location;

    scooters = scooter_controller->search_by_location(scooter_location);

    if(scooters.empty()){

        cout<<"There are no scooters with this location.\n";

    }
    else{

        for(auto &scooter: scooters){

            cout<<scooter.get_identifier()<<" "<<scooter.get_model()<<" "<<scooter.get_commissioning_date()<<" "<<scooter.get_kilometer()<<" "<<scooter.get_location()<<" "<<scooter.get_condition()<<"\n";

        }

    }

}

void Electric_scooter_UI::Filter_by_commissioning_date() {

    string scooter_date;
    vector <Electric_scooter> scooters;

    cout<<"Show scooters with the commissioning date less than which date? (Format: YYYYMMDD, where YYYY = year, MM = month, DD = day)\n";
    cin>>scooter_date;

    scooters = scooter_controller->filter_by_commissioning_date(scooter_date);

    if(scooters.empty()){

        cout<<"There are no scooters with an older commissioning date than the given one.\n";

    }
    else{

        for(auto &scooter: scooters){

            cout<<scooter.get_identifier()<<" "<<scooter.get_model()<<" "<<scooter.get_commissioning_date()<<" "<<scooter.get_kilometer()<<" "<<scooter.get_location()<<" "<<scooter.get_condition()<<"\n";

        }

    }

}

void Electric_scooter_UI::Filter_by_kilometer() {

    int scooter_kilometer;
    vector <Electric_scooter> scooters;

    cout<<"Show scooters with a number of kilometers less than?\n";
    cin>>scooter_kilometer;

    scooters = scooter_controller->filter_by_kilometer(scooter_kilometer);

    if(scooters.empty()){

        cout<<"There are no scooters with fewer kilometers than the given value.\n";

    }
    else{

        for(auto &scooter: scooters){

            cout<<scooter.get_identifier()<<" "<<scooter.get_model()<<" "<<scooter.get_commissioning_date()<<" "<<scooter.get_kilometer()<<" "<<scooter.get_location()<<" "<<scooter.get_condition()<<"\n";

        }

    }

}

void Electric_scooter_UI::Sort_by_commissioning_date(){

    vector <Electric_scooter> scooters;

    scooters = scooter_controller->sort_by_commissioning_date();

        for(auto &scooter: scooters){

            cout<<scooter.get_identifier()<<" "<<scooter.get_model()<<" "<<scooter.get_commissioning_date()<<" "<<scooter.get_kilometer()<<" "<<scooter.get_location()<<" "<<scooter.get_condition()<<"\n";

        }

}

void Electric_scooter_UI::Modify_condition() {

    string scooter_identifier;

    cout<<"Identifier? (Format: LLL, where L = letter)\n";
    cin>>scooter_identifier;

    if(!scooter_controller->exists(scooter_identifier))
        cout<<"Scooter does not exist.\n";
    else
        scooter_controller->modify_condition(scooter_identifier);

}

void Electric_scooter_UI::Reserve_scooter(){

    string scooter_identifier;

    cout<<"Identifier? (Format: LLL, where L = letter)\n";
    cin>>scooter_identifier;

    if(!scooter_controller->exists(scooter_identifier))
        cout<<"Scooter does not exist.\n";
    else
        scooter_controller->reserve_scooter(scooter_identifier);

}

void Electric_scooter_UI::See_reserved_scooters(){

    vector <Electric_scooter> scooters = scooter_controller->see_reserved_scooters();

    for(auto &scooter: scooters){

        cout<<scooter.get_identifier()<<" "<<scooter.get_model()<<" "<<scooter.get_commissioning_date()<<" "<<scooter.get_kilometer()<<" "<<scooter.get_location()<<" "<<scooter.get_condition()<<"\n";

    }

}

