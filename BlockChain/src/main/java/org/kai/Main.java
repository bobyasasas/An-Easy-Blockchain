package org.kai;


import java.io.*;
import java.util.Scanner;


public class Main {
    public static void dataStorage() throws IOException {
        System.out.println("请输入需要存储的数据名:");
        Scanner in = new Scanner(System.in);
        String fileName = in.nextLine();
        if (MyFile.ifTxtExists(fileName)) {
            String conFileName = fileName + "_Con";
            MyFile.delTxt(conFileName);
            MyFile.createTxt(conFileName);
            FileReader fileReader = new FileReader("./data/" + fileName + ".txt");
            FileWriter fileWriter = new FileWriter("./data/" + conFileName + ".txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String data;
            data = bufferedReader.readLine();
            int previousIndex = 0;
            long previousTimestamp = 0;
            Block initialBlock = new Block(previousIndex, Md5.md5("kai"), data, previousTimestamp);
            bufferedWriter.write(initialBlock.getHash() + " " + initialBlock.getTimestamp() + "\n");
            previousIndex = initialBlock.getIndex();
            previousTimestamp = initialBlock.getTimestamp();
            while ((data = bufferedReader.readLine()) != null) {
                Block block = new Block(previousIndex, Md5.md5(data), data, previousTimestamp);
                bufferedWriter.write(block.getHash() + " " + block.getTimestamp() + "\n");
                previousTimestamp = block.getTimestamp();
                previousIndex = block.getIndex();
            }
            bufferedReader.close();
            bufferedWriter.close();
            System.out.println("数据储存完毕.");
        } else {
            System.out.println("没有找到数据文件" + "\'" + fileName + ".txt\'");
        }
    }

    public static void dataConfirm() throws IOException {
        System.out.println("请输入需要验证的数据名:");
        Scanner in = new Scanner(System.in);
        String fileName = in.nextLine();
        String conFileName = fileName + "_Con";
        if (MyFile.ifTxtExists(fileName) && MyFile.ifTxtExists(conFileName)) {
            FileReader oldFile = new FileReader("./data/" + fileName + ".txt");
            FileReader conFile = new FileReader("./data/" + conFileName + ".txt");
            BufferedReader oldReader = new BufferedReader(oldFile);
            BufferedReader conReader = new BufferedReader(conFile);
            String data;
            data = oldReader.readLine();
            Line line = new Line(conReader.readLine());
            int previousIndex = 0;
            long previousTimestamp = 0;
            Block initialBlock = new Block(previousIndex, Md5.md5("kai"), data, previousTimestamp, line.timestamp);
            previousIndex = initialBlock.getIndex();
            previousTimestamp = initialBlock.getTimestamp();
            Boolean con = true;
            while ((data = oldReader.readLine()) != null) {
                line=new Line(conReader.readLine());
                Block block = new Block(previousIndex, Md5.md5(data), data, previousTimestamp, line.timestamp);
                if(!block.getHash().equals(line.hash)){
                    System.out.println("第"+block.getIndex()+"行数据遭到修改.");
                    con=false;
                    break;
                }
                previousTimestamp = block.getTimestamp();
                previousIndex = block.getIndex();
            }
            if(con){
                System.out.println("数据未被修改.");
            }
            oldReader.close();
            conReader.close();
        } else {
            System.out.println("数据未保存.");
        }
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            Scanner pause = new Scanner(System.in);
            pause.nextLine();
            System.out.println("请选择功能：");
            System.out.println("1.储存数据.");
            System.out.println("2.验证数据是否更改.");
            System.out.println("0.退出程序.");
            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();
            if (i == 1) {
                Main.dataStorage();
            } else if (i == 2) {
                Main.dataConfirm();
            } else if (i == 0) {
                break;
            } else {
                System.out.println("请输入正确的指令");
            }
        }
    }
}