import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
 
public class test1
{
    public String[] solution(String[] record) {
        Map<String, String> idMap = new HashMap<>();
        List<String[]> tmp = new LinkedList<>();
 
        for (String records : record)
        {
            String[] keyWord = records.split(" ");
            if (keyWord[0].equals("Enter"))
            {
                idMap.put(keyWord[1], keyWord[2]);
                tmp.add(keyWord);
            } else if (keyWord[0].equals("Change"))
            {
                idMap.put(keyWord[1], keyWord[2]);
            } else
            {
                tmp.add(keyWord);
            }
        }
 
        String[] answer = new String[tmp.size()];
        int idx = 0;
        for (String[] keyWords : tmp)
        {
            String nickName = idMap.get(keyWords[1]);
            if (keyWords[0].equals("Enter"))
                answer[idx++] = nickName + "님이 들어왔습니다.";
            else
                answer[idx++] = nickName + "님이 나갔습니다.";
        }
        return answer;
    }
}
