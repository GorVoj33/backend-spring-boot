package test.project.myproject.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import test.project.myproject.dao.AddressDAO;
import test.project.myproject.dao.FinalOrderDAO;
import test.project.myproject.dao.OrderItemDAO;
import test.project.myproject.dao.ProductDAO;
import test.project.myproject.domain.Address;
import test.project.myproject.domain.Cart;
import test.project.myproject.domain.FinalOrder;
import test.project.myproject.domain.OrderItem;
import test.project.myproject.domain.Product;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FinalOrderRestController {
	@Autowired
	AddressDAO addressDAO;
	@Autowired
	FinalOrderDAO finalOrderDAO;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	OrderItemDAO orderItemDAO;

	@PostMapping(value = "/rest/order")
	public FinalOrder saveProduct(@RequestBody FinalOrder order){
	//public ResponseEntity<FinalOrder> saveProduct(@RequestBody FinalOrder order){
		order.setDone(false);
//		System.out.println("SAVE ITEMS");
//		System.out.println("Duzina: "+order.getItems().size());
//		for(OrderItem oi : order.getItems()) {
//			System.out.println("Item  : "+oi.getProduct().getName()+" ,"+oi.getTotal());
//			
//		}
		
		Address a = order.getAddress();
		System.out.println("Adresa: "+a.getAddress()+"  ...  "+a.getCity());
		a = addressDAO.save(a);
//		for(OrderItem item : order.getItems()) {
//			System.out.println("Item: "+item.getQuantity()+" total: "+item.getTotal()+" --->" +item.getProduct().getName());
//			item.setFinalOrder(order);
//			//orderItemDAO.save();
//		}
		order = finalOrderDAO.save(order);
		//order.setDate(new Date());
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdPr.getId()).toUri();
		//return new ResponseEntity<FinalOrder>(order,HttpStatus.OK);
		return order;
	}
	
	@GetMapping(value = "/rest/orders")
	public List<FinalOrder> getAll(){
		List<FinalOrder> list = finalOrderDAO.getAll();
		for(FinalOrder order: list) {
			System.out.println("Total: "+order.getTotal());
			for(OrderItem item : order.getItems()) {
				System.out.println("Naziv: "+item.getProduct().getName());
				
			}
		}
		return list;
	}
	@GetMapping(value = "/rest/orders/{id}/items")
	public List<OrderItem> getOrderItems(@PathVariable("id") Long id){
		List<FinalOrder> list = finalOrderDAO.getAll();
		List<OrderItem> temp = new ArrayList<OrderItem>();
		for(FinalOrder order: list) {
			if(order.getId()==id) {
				temp = order.getItems();
				return temp;
			}
			
		}
		return temp;
	}
	@PutMapping(value = "/rest/orders/finish-order/{id}")
	public boolean finishOrder(@PathVariable("id") Long id, @RequestBody FinalOrder order) {
		order.setDone(true);
		finalOrderDAO.save(order);
		return true;
	}
	
//	@PostMapping(value = "/rest/order/{id}/items")
//	public boolean saveProduct(@PathVariable("id") Long id, @RequestBody List<OrderItem> items){
//	//public ResponseEntity<FinalOrder> saveProduct(@RequestBody FinalOrder order){
////		System.out.println("SAVE ITEMS");
////		System.out.println("Duzina: "+order.getItems().size());
////		for(OrderItem oi : order.getItems()) {
////			System.out.println("Item  : "+oi.getProduct().getName()+" ,"+oi.getTotal());
////			
////		}
//		System.out.println("CUVANJE LISTE ITEMA: ID : "+id);
//		for(OrderItem item : items) {
//			System.out.println("Item: "+item.getQuantity()+" total: "+item.getTotal()+" --->" +item.getProduct().getName());
//			//item.setFinalOrder(order);
//			//finalOrderDAO.saveItem(item);
//		}
//		
//		
////		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdPr.getId()).toUri();
//		//return new ResponseEntity<FinalOrder>(order,HttpStatus.OK);
//		return true;
//	}
	@PostMapping(value = "/rest/order/{id}/items/save")
	public boolean saveItem(@PathVariable("id") Long id, @RequestBody OrderItem item){
	//public ResponseEntity<FinalOrder> saveProduct(@RequestBody FinalOrder order){
//		System.out.println("SAVE ITEMS");
//		System.out.println("Duzina: "+order.getItems().size());
//		for(OrderItem oi : order.getItems()) {
//			System.out.println("Item  : "+oi.getProduct().getName()+" ,"+oi.getTotal());
//			
//		}
		FinalOrder o = finalOrderDAO.getById(id);
		System.out.println("CUVANJE NOVOG ITEMA: ID : "+id);
		item.setFinalOrder(o);
		Product product = item.getProduct();
		System.out.println("Pre udpdate product: "+product.getUnitsInStock());
		product.setUnitsInStock(product.getUnitsInStock()-item.getQuantity());
		System.out.println("Posle udpdate product: "+product.getUnitsInStock());
		productDAO.save(product);
		System.out.println("Nakon final udpdate product: "+product.getUnitsInStock());
		orderItemDAO.save(item);
		//for(OrderItem item : items) {
		System.out.println("Item: "+item.getQuantity()+" total: "+item.getTotal()+" --->" +item.getProduct().getName());
			//item.setFinalOrder(order);
			//finalOrderDAO.saveItem(item);
		//}
		
		
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdPr.getId()).toUri();
		//return new ResponseEntity<FinalOrder>(order,HttpStatus.OK);
		return true;
	}
	
}
