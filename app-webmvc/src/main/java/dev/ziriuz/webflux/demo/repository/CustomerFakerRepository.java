package dev.ziriuz.webflux.demo.repository;

import dev.ziriuz.webflux.demo.domain.model.ContactDetails;
import dev.ziriuz.webflux.demo.domain.model.CustomerDetails;
import dev.ziriuz.webflux.demo.domain.port.output.CustomerRepository;
import dev.ziriuz.webflux.demo.util.Utils;

public class CustomerFakerRepository implements CustomerRepository {

    @Override
    public CustomerDetails getCustomerDetailsById(String id) {
        return buildFakeCustomerDetails(id);
    }
    private CustomerDetails buildFakeCustomerDetails(String id) {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCustomerId(id);
        customerDetails.setName(Utils.faker().company().name());
        customerDetails.setContactDetails(new ContactDetails(
                Utils.faker().internet().emailAddress(),
                Utils.faker().phoneNumber().phoneNumber(),
                Utils.faker().address().fullAddress()
        ));
        return customerDetails;
    }
}
