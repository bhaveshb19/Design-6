public class AutoCompleteSearch {

    class TrieNode {
        TrieNode[] childrens;
        List<String> li;

        public TrieNode() {
            this.childrens = new TrieNode[126];
            this.li = new ArrayList<>();
        }
    }

    HashMap<String, Integer> map;
    TrieNode root;
    StringBuilder sb;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.root = new TrieNode();
        this.sb = new StringBuilder();
        for(int i=0;i<sentences.length;i++){
            String str = sentences[i];
            int freq = times[i];
            map.put(str,map.getOrDefault(str,0)+freq);
            insert(str);
        }

    }

    public void insert(String str) {
        TrieNode curr = root;
        for (char ch : str.toCharArray()) {
            if (curr.childrens[ch - ' '] == null) {
                curr.childrens[ch - ' '] = new TrieNode();
            }
            curr = curr.childrens[ch - ' '];
            List<String> list = curr.li;
            if (!list.contains(str)) {
                list.add(str);
            }
            Collections.sort(list, (s1, s2) -> {
                if (map.get(s1) == map.get(s2)) {
                    return s1.compareTo(s2);
                } else {
                    return map.get(s2) - map.get(s1);
                }
            });

            if (list.size() > 3) {
                list.remove(list.size() - 1);
            }

        }
    }

    public List<String> startsWith(String str) {
        TrieNode curr = root;
        for (char ch : str.toCharArray()) {
            curr = curr.childrens[ch - ' '];
            if (curr == null)
                return new ArrayList<>();
        }
        return curr.li;
    }

    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        if (c == '#') {
            String str = sb.toString();
            map.put(str, map.getOrDefault(str, 0) + 1);
            insert(str);
            sb = new StringBuilder();
            return result;
        }
        sb.append(c);
        result = startsWith(sb.toString());
        return result;
    }
}