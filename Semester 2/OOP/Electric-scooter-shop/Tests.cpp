#include "Tests.h"
#include "Controller.h"
#include <cassert>
#include <iostream>
#include <memory>
#include <vector>

using namespace Controller;

void Test_All(){

    Test_Add();
    Test_Delete();
    Test_Edit();
    Test_Search_by_location();
    Test_Filter_by_commissioning_date();
    Test_Filter_by_kilometer();
    Test_Sort_by_date();
    Test_Modify_condition();

    cout<<"\n";

}

void Test_Add(){

    cout<<"Test Add\n";

    shared_ptr <Electric_scooter_Controller> Electric_Scooter_Controller = make_shared<Electric_scooter_Controller>();
    Electric_Scooter_Controller->InMemory();

    assert(Electric_Scooter_Controller->exists("OLD") == false);

    Electric_Scooter_Controller->add("OLD", "01", "18000305", 199922291, "Hamburg", "out-of-use");

    assert(Electric_Scooter_Controller->get_all().begin()->get_identifier() == "OLD");
    assert(Electric_Scooter_Controller->get_all().begin()->get_model() == "01");
    assert(Electric_Scooter_Controller->get_all().begin()->get_commissioning_date() == "18000305");
    assert(Electric_Scooter_Controller->get_all().begin()->get_kilometer() == 199922291);
    assert(Electric_Scooter_Controller->get_all().begin()->get_location() == "Hamburg");
    assert(Electric_Scooter_Controller->get_all().begin()->get_condition() == "out-of-use");

}

void Test_Delete(){

    cout<<"Test Delete\n";

    shared_ptr <InMemory_Repo> Electric_Scooter_Repository = make_shared<InMemory_Repo>();
    shared_ptr <Electric_scooter_Controller> Electric_Scooter_Controller = make_shared<Electric_scooter_Controller>();
    Electric_Scooter_Controller->InMemory();

    Electric_Scooter_Controller->add("OLD", "01", "18000305", 199922291, "Hamburg", "out-of-use");
    Electric_Scooter_Controller->add("MMM", "MM66", "20290101", 0, "Kyev", "waiting");
    Electric_Scooter_Controller->del("OLD");

    assert(Electric_Scooter_Controller->exists("OLD") == false);

}

void Test_Edit(){

    cout<<"Test Edit\n";

    shared_ptr <Electric_scooter_Controller> Electric_Scooter_Controller = make_shared<Electric_scooter_Controller>();
    Electric_Scooter_Controller->InMemory();

    Electric_Scooter_Controller->add("MMM", "MM66", "20290101", 0, "Kyev", "waiting");
    Electric_Scooter_Controller->edit("MMM", "01", "18000305", 199922291, "Hamburg", "out-of-use");

    assert(Electric_Scooter_Controller->get_all().begin()->get_identifier() == "MMM");
    assert(Electric_Scooter_Controller->get_all().begin()->get_model() == "01");
    assert(Electric_Scooter_Controller->get_all().begin()->get_commissioning_date() == "18000305");
    assert(Electric_Scooter_Controller->get_all().begin()->get_kilometer() == 199922291);
    assert(Electric_Scooter_Controller->get_all().begin()->get_location() == "Hamburg");
    assert(Electric_Scooter_Controller->get_all().begin()->get_condition() == "out-of-use");

}

void Test_Search_by_location(){

    cout<<"Test Search by location\n";

    shared_ptr <Electric_scooter_Controller> Electric_Scooter_Controller = make_shared<Electric_scooter_Controller>();
    Electric_Scooter_Controller->InMemory();

    Electric_Scooter_Controller->add("XXX", "X6", "20220302", 15002, "Berlin", "reserved");
    Electric_Scooter_Controller->add("MGL", "S10", "20240312", 1, "Dubai", "waiting");
    Electric_Scooter_Controller->add("BMW", "E92", "20010322", 1500982, "Slobozia", "parked");
    Electric_Scooter_Controller->add("PCY", "Z100", "21001231", 0, "Milano", "waiting");
    Electric_Scooter_Controller->add("DCA", "1310", "19801102", 22000002, "Medias", "out-of-use");
    Electric_Scooter_Controller->add("TYO", "X69", "20150302", 300002, "Amsterdam", "in-use");
    Electric_Scooter_Controller->add("TTO", "Y69", "20180322", 3012002, "Riad", "waiting");
    Electric_Scooter_Controller->add("MMM", "MM66", "20290101", 0, "Kiev", "waiting");
    Electric_Scooter_Controller->add("ZZZ", "Z01", "19990406", 1500000, "Amsterdam", "in-use");
    Electric_Scooter_Controller->add("OLD", "01", "18000305", 199922291, "Hamburg", "out-of-use");

    vector <Electric_scooter> scooters = Electric_Scooter_Controller->search_by_location("Kiev");

    for(auto scooter : scooters){

        assert(scooter.get_location() == "Kiev");

    }

}

void Test_Filter_by_commissioning_date(){

    cout<<"Test Filter by commissioning date\n";

    shared_ptr <Electric_scooter_Controller> Electric_Scooter_Controller = make_shared<Electric_scooter_Controller>();
    Electric_Scooter_Controller->InMemory();

    Electric_Scooter_Controller->add("XXX", "X6", "20220302", 15002, "Berlin", "reserved");
    Electric_Scooter_Controller->add("MGL", "S10", "20240312", 1, "Dubai", "waiting");
    Electric_Scooter_Controller->add("BMW", "E92", "20010322", 1500982, "Slobozia", "parked");
    Electric_Scooter_Controller->add("PCY", "Z100", "21001231", 0, "Milano", "waiting");
    Electric_Scooter_Controller->add("DCA", "1310", "19801102", 22000002, "Medias", "out-of-use");
    Electric_Scooter_Controller->add("TYO", "X69", "20150302", 300002, "Amsterdam", "in-use");
    Electric_Scooter_Controller->add("TTO", "Y69", "20180322", 3012002, "Riad", "waiting");
    Electric_Scooter_Controller->add("MMM", "MM66", "20290101", 0, "Kiev", "waiting");
    Electric_Scooter_Controller->add("ZZZ", "Z01", "19990406", 1500000, "Amsterdam", "in-use");
    Electric_Scooter_Controller->add("OLD", "01", "18000305", 199922291, "Hamburg", "out-of-use");

    vector <Electric_scooter> scooters = Electric_Scooter_Controller->filter_by_commissioning_date("20160101");

    for(auto scooter : scooters){

        assert(scooter.get_commissioning_date() < "20160101");

    }

}

void Test_Filter_by_kilometer(){

    cout<<"Test Filter by kilometer\n";

    shared_ptr <Electric_scooter_Controller> Electric_Scooter_Controller = make_shared<Electric_scooter_Controller>();
    Electric_Scooter_Controller->InMemory();

    Electric_Scooter_Controller->add("XXX", "X6", "20220302", 15002, "Berlin", "reserved");
    Electric_Scooter_Controller->add("MGL", "S10", "20240312", 1, "Dubai", "waiting");
    Electric_Scooter_Controller->add("BMW", "E92", "20010322", 1500982, "Slobozia", "parked");
    Electric_Scooter_Controller->add("PCY", "Z100", "21001231", 0, "Milano", "waiting");
    Electric_Scooter_Controller->add("DCA", "1310", "19801102", 22000002, "Medias", "out-of-use");
    Electric_Scooter_Controller->add("TYO", "X69", "20150302", 300002, "Amsterdam", "in-use");
    Electric_Scooter_Controller->add("TTO", "Y69", "20180322", 3012002, "Riad", "waiting");
    Electric_Scooter_Controller->add("MMM", "MM66", "20290101", 0, "Kiev", "waiting");
    Electric_Scooter_Controller->add("ZZZ", "Z01", "19990406", 1500000, "Amsterdam", "in-use");
    Electric_Scooter_Controller->add("OLD", "01", "18000305", 199922291, "Hamburg", "out-of-use");

    vector <Electric_scooter> scooters = Electric_Scooter_Controller->filter_by_kilometer(200000);

    for(auto scooter : scooters){

        assert(scooter.get_kilometer() < 20000);

    }

}

void Test_Sort_by_date(){

    cout<<"Test Sort by date\n";

    shared_ptr <Electric_scooter_Controller> Electric_Scooter_Controller = make_shared<Electric_scooter_Controller>();
    Electric_Scooter_Controller->InMemory();

    Electric_Scooter_Controller->add("XXX", "X6", "20220302", 15002, "Berlin", "reserved");
    Electric_Scooter_Controller->add("MGL", "S10", "20240312", 1, "Dubai", "waiting");
    Electric_Scooter_Controller->add("BMW", "E92", "20010322", 1500982, "Slobozia", "parked");
    Electric_Scooter_Controller->add("PCY", "Z100", "21001231", 0, "Milano", "waiting");
    Electric_Scooter_Controller->add("DCA", "1310", "19801102", 22000002, "Medias", "out-of-use");
    Electric_Scooter_Controller->add("TYO", "X69", "20150302", 300002, "Amsterdam", "in-use");

    vector <Electric_scooter> scooters = Electric_Scooter_Controller->sort_by_commissioning_date();
    vector <string> dates = {"19801102", "20010322", "20150302", "20220302", "20240312", "21001231"};

    int i=0;
    for (auto scooter = scooters.begin(); scooter < scooters.end(); scooter++){

        assert(scooter->get_commissioning_date() == dates[i]);
        i++;

    }

}

void Test_Modify_condition(){

    cout<<"Test Modify condition\n";

    shared_ptr <Electric_scooter_Controller> Electric_Scooter_Controller = make_shared<Electric_scooter_Controller>();
    Electric_Scooter_Controller->InMemory();

    Electric_Scooter_Controller->add("OLD", "01", "18000305", 199922291, "Hamburg", "out-of-use");

    Electric_Scooter_Controller->modify_condition("OLD");

    assert(Electric_Scooter_Controller->get_all().begin()->get_identifier() == "OLD" && Electric_Scooter_Controller->get_all().begin()->get_condition() == "in-use");


}