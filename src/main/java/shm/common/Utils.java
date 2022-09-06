package shm.common;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import shm.DAO.NhanVienDAO;
import shm.DAOImpl.NhanVienDAOImpl;
import shm.entity.NhanVien;
import shm.model.Revenue;

public class Utils {
	public static String createUserName(SessionFactory factory, String fullName) {
		fullName = Utils.covertToString(fullName);
		String res="";
		String[] temp = fullName.split(" ");
		
		res=temp[temp.length-1];
		for(int i=0; i< temp.length-1 ;i++) {
			res+= temp[i].charAt(0);
		}
		int number = new NhanVienDAOImpl().getMaxNumberByName(factory, res) + 1;
		res+=number;
		return res.toUpperCase();
	}
	
	public static int createID(SessionFactory factory, String hql) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Integer> list = query.list();
		if(list.get(0) == null) {
			return 1;
		}
		return list.get(0);
	}
	
	public static String covertToString(String value) {
	      try {
	            String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
	            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	            return pattern.matcher(temp).replaceAll("");
	     } catch (Exception ex) {
	            ex.printStackTrace();
	      }
	      return null;
	}

	public List<Revenue> getRevenue(List<Revenue> revenues, String from, String to) {
		Integer start = Integer.parseInt(from.substring(5, 7));
		Integer end = Integer.parseInt(to.substring(5, 7));
		List<Revenue> list = new ArrayList<Revenue>();
		Integer yearFrom = Integer.parseInt(from.substring(0, 4));
		Integer yearTo = Integer.parseInt(to.substring(0, 4));
		for (int i = yearFrom; i <= yearTo; i++) {
			int s = 1, e = 12;
			if (i == yearFrom)
				s = start;
			if (i == yearTo)
				e = end;
			Boolean flag = false;
			for (Revenue r : revenues) {
				if (r.getYear() == i) {
					flag = true;
					break;
				}
			}
			if (flag) {
				for (int j = s; j <= e; j++) {
					Boolean f = false;
					for (Revenue r : revenues) {
						if (r.getYear() == i && r.getMonth() == j) {
							f = true;
							list.add(new Revenue(i, j, r.getTotal()));
							break;
						}
					}
					if (!f)
						list.add(new Revenue(i, j, 0L));
				}
			} else {
				list.add(new Revenue(i, 0, 0L));
			}
		}
		return list;
	}
	
	public static String getMST() {
		String result="";
		for(int i=1; i<=10; i++) {
			result+= new Random().nextInt(9) +1;
		}
		return result;
	}
	
}
