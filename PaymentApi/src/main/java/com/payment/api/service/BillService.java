package com.payment.api.service;

import java.util.List;

import com.payment.api.model.bill.Bill;
import com.payment.api.model.bill.BillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    
    @Autowired
    private BillRepository billRepository;

    public Bill findBillById(Long id){
        return billRepository.findById(id).get();
    }

    public Bill findBillByClientOrderId(Long id){
        List<Bill> bills = billRepository.findByClientOrderId(id);
        if(!bills.isEmpty()){
            return bills.get(0);
        }
        return null;
    }

    public List<Bill> findAllBills(){
        return billRepository.findAll();
    }

    public Bill createBill(Bill newBill){
        if(newBill != null)
            return billRepository.save(newBill);
        else
            return null;
    }

    public void updateBill(Long id, Bill updatedBill){
        Bill foundBill = this.findBillById(id);
        if(foundBill != null){
            foundBill.setTotalCost(updatedBill.getTotalCost());
            foundBill.setClientOrder(updatedBill.getClientOrder());
            billRepository.save(foundBill);
        }
    }

    public void deleteBill(Long id){
        Bill bill = this.findBillById(id);
        billRepository.delete(bill);
    }

}
