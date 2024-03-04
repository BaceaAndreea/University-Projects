#include "Domain.h"

using namespace Domain;

Electric_scooter::Electric_scooter(string scooter_identifier, string scooter_model, string scooter_commissioning_date, int scooter_kilometer, string scooter_location, string scooter_condition) {

    this->identifier = scooter_identifier;
    this->commissioning_date = scooter_commissioning_date;
    this->condition = scooter_condition;
    this->kilometer = scooter_kilometer;
    this->location = scooter_location;
    this->model = scooter_model;

}

string Electric_scooter::get_identifier() {
    return identifier;
}

string Electric_scooter::get_commissioning_date() {
    return commissioning_date;
}

string Electric_scooter::get_condition() {
    return condition;
}

int Electric_scooter::get_kilometer() const {
    return kilometer;
}

string Electric_scooter::get_location() {
    return location;
}
string Electric_scooter::get_model() {
    return model;
}

void Electric_scooter::set_identifier(string& scooter_identifier) {
    this->identifier = scooter_identifier;
}

void Electric_scooter::set_commissioning_date(string& scooter_commissioning_date) {
    this->commissioning_date = scooter_commissioning_date;
}
void Electric_scooter::set_condition(string& scooter_condition) {
    this->condition = scooter_condition;
}
void Electric_scooter::set_kilometer(int& scooter_kilometer) {
    this->kilometer = scooter_kilometer;
}
void Electric_scooter::set_location(string& scooter_location) {
    this->location = scooter_location;
}
void Electric_scooter::set_model(string& scooter_model) {
    this->model = scooter_model;
}