//                  else if (s.equals("/out")) {
//                     out.println(">>방에 나가셨습니다.");
//                     out.println("대기실로 이동합니다.");
//                     ShowMeue();
//
//                     int rn = 0;
//                     Iterator<String> it = null;
//                     for (int i = 0; i < chat.length; i++) {
//                        if (chat[i] != null) {
//                           it = chat[i].clientMap2.keySet().iterator();
//                        } else {
//                           break;
//                        }
//                        while (it.hasNext()) {
//                           if (id.equals(it.next())) {
//                              rn = i;
//                           }
//                        }
//                     }
////방장이랑 나가는 사람이랑 같을 때
////리더 바꾸기                      
//                     it = chat[rn].clientMap2.keySet().iterator();
//                     if (id == chat[rn].chatleader) {
//                        while (true) {
//                           chat[rn].chatleader = it.next();
//                           if (id != chat[rn].chatleader) {
//                              break;
//                           }
//                        }
//
//                     }
//                     chat[rn].clientMap2.remove(id);
//                     chat[0].clientMap2.put(id, out);
//
//                     sendroomMsg(chat[rn].chatleader,"님이 방장이 되셨습니다.\n");
//                     leaderMsg(chat[rn].chatleader);