package dev.ziriuz.webflux.demo.repository;

import dev.ziriuz.webflux.demo.domain.model.ContactDetails;
import dev.ziriuz.webflux.demo.domain.model.CustomerDetails;
import dev.ziriuz.webflux.demo.domain.port.output.CustomerRepository;
import dev.ziriuz.webflux.demo.util.Utils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class CustomerFakerRepository implements CustomerRepository {

    @Override
    public Mono<CustomerDetails> getCustomerDetailsById(String id) {
        return Mono.fromSupplier(() -> buildFakeCustomerDetails(id))
                .subscribeOn(Schedulers.boundedElastic());
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
