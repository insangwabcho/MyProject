package kwanwoo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MultiServer {
	public static void main(String[] args) {
		MultiServer ms = new MultiServer(); //������ü
		ms.init();//����
	}
	HashMap hm;
	ServerSocket serverSocket = null;
	Socket socket = null;
	//������
	public MultiServer(){
		hm = new HashMap();
		Collections.synchronizedMap(hm);
	}
	public void init(){
		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("������ ���۵Ǿ����ϴ�.");
			while(true){//������ ����Ǵ� ���� Ŭ���̾�Ʈ���� ������ ��ٸ�.
				socket = serverSocket.accept();
		//Ŭ���̾�Ʈ�� ������ ��ٸ��ٰ� ������ �Ǹ� Socket��ü�� ����		
				System.out.println(socket.getInetAddress()+":"+socket.getPort());
		//Ŭ���̾�Ʈ ����(ip,port)���
				Thread msr = new MultiServerRec(socket);//���������
				msr.start();//��������
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//���� �� ��� Ŭ���̾�Ʈ�鿡�� �޽����� ����.
	public void sendAllMsg(String msg){
	//��½�Ʈ���� ���������� ���ͼ� �ش� �޽����� ����Ѵ�.
		Iterator it = hm.keySet().iterator();
		
		while(it.hasNext()){
			try{
				DataOutputStream it_out = (DataOutputStream)hm.get(it.next());
				it_out.writeUTF(msg);
				
			}catch (Exception e) {
				System.out.println("���� : "+e);
			}
		}
	}
//client�κ��� �о�� �޽����� �ٸ� client(socket)�� ������ ������ �ϴ� method	
	class MultiServerRec extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
		//������
	public MultiServerRec(Socket socket){
		this.socket = socket;
		try {
		//��Ĺ���κ��� �Է½�Ʈ���� ��´�.
			in = new DataInputStream(socket.getInputStream());
		//��½�Ʈ��
			out = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			System.out.println("���� : "+e);
		}
	}
	public void run() {//thread�� ����ϱ����ؼ� run�޼ҵ� ������
		String name=""; //Ŭ���̾�Ʈ�κ��� ���� �̸��� ������ ����.
		try {
			name = in.readUTF();//client���� ó�����κ����� �޽����� client�� ����� �̸�.
			sendAllMsg(name + "���� �����ϼ̽��ϴ�.");
	//���簴ü�� ������ �ִ� ��Ĺ�� �����ϰ� �ٸ� ���ϵ鿡�� ������ �˸�.
			hm.put(name,out);//�ؽ��ʿ� Ű��  name����  ��½�Ʈ�� ��ü�� ����.
			System.out.println("���� ������ ����"+hm.size()+"�� �Դϴ�.");
			while(in != null){//�Է½�Ʈ���� null�� �ƴϸ� �ݺ�
				sendAllMsg(in.readUTF());//���� ���Ͽ��� �о�޽����� �ؽ��ʿ� ����� ���
				//��½�Ʈ������ ������.
			}
		} catch (Exception e) {
			System.out.println(e+"---->");
		}finally{
	//���ܰ� �߻��� �� ����, �ؽ��ʿ��� �ش� ������ ����.
	//���� �����ϰų� ������ java.net.SocketException: ���ܹ߻�
		hm.remove(name);
		sendAllMsg(name+"���� �����ϼ̽��ϴ�.");
		System.out.println("���� ������ ����"+hm.size()+"�� �Դϴ�.");
		}
	}
	}
}





