#include<iostream>
#include<vector>
#include<string>
#include<set>
#include<stack>

using namespace std;


struct transition {
	int vertex_from;
	int vertex_to;
	char transition_symbol;
};


class Non_Finite_Automata {
public:
	vector<int> vertex;
	vector<transition> transitions;
	int final_state;

	Non_Finite_Automata() {

	}

	int get_vertex_count() {
		return vertex.size();
	}

	void set_vertex(int no_vertex) {
		for(int i = 0; i < no_vertex; i++) {
			vertex.push_back(i);
		}
	}

	void set_transition(int vertex_from, int vertex_to, char transition_symbol) {
		transition new_transition;
		new_transition.vertex_from = vertex_from;
		new_transition.vertex_to = vertex_to;
		new_transition.transition_symbol = transition_symbol;
		transitions.push_back(new_transition);
	}

	void set_final_state(int fs) {
		final_state = fs;
	}

	int get_final_state() {
		return final_state;
	}

	void display() {
		transition new_transition;
		cout<<"\n";
		for(int i = 0; i < transitions.size(); i++) {
			new_transition = transitions.at(i);
			cout<<"q"<<new_transition.vertex_from<<" --> q"<<new_transition.vertex_to<<" : Symbol - "<<new_transition.transition_symbol<<endl;
		}
		cout<<"\nThe final state is q"<<get_final_state()<<endl;
	}
};



Non_Finite_Automata concatinate(Non_Finite_Automata a, Non_Finite_Automata b) {
	Non_Finite_Automata result;
	result.set_vertex(a.get_vertex_count() + b.get_vertex_count());
	int i;
	transition new_transition;

	for(i = 0; i < a.transitions.size(); i++) {
		new_transition = a.transitions.at(i);
		result.set_transition(new_transition.vertex_from, new_transition.vertex_to, new_transition.transition_symbol);
	}

	result.set_transition(a.get_final_state(), a.get_vertex_count(), '^');

	for(i = 0; i < b.transitions.size(); i++) {
		new_transition = b.transitions.at(i);
		result.set_transition(new_transition.vertex_from + a.get_vertex_count(), new_transition.vertex_to + a.get_vertex_count(), new_transition.transition_symbol);
	}

	result.set_final_state(a.get_vertex_count() + b.get_vertex_count() - 1);

	return result;
}


Non_Finite_Automata STAR(Non_Finite_Automata a) {
	Non_Finite_Automata result;
	int i;
	transition new_transition;
	
	result.set_vertex(a.get_vertex_count() + 2);

	result.set_transition(0, 1, '^');

	for(i = 0; i < a.transitions.size(); i++) {
		new_transition = a.transitions.at(i);
		result.set_transition(new_transition.vertex_from + 1, new_transition.vertex_to + 1, new_transition.transition_symbol);
	}

	result.set_transition(a.get_vertex_count(), a.get_vertex_count() + 1, '^');
	result.set_transition(a.get_vertex_count(), 1, '^');
	result.set_transition(0, a.get_vertex_count() + 1, '^');

	result.set_final_state(a.get_vertex_count() + 1);

	return result;
}


Non_Finite_Automata or_selection(vector<Non_Finite_Automata> selections, int no_of_selections) {
	Non_Finite_Automata result;
	int vertex_count = 2;
	int i, j;
	Non_Finite_Automata med;
	transition new_transition;

	for(i = 0; i < no_of_selections; i++) {
		vertex_count += selections.at(i).get_vertex_count();
	}

	result.set_vertex(vertex_count);
	
	int adder_track = 1;

	for(i = 0; i < no_of_selections; i++) {
		result.set_transition(0, adder_track, '^');
		med = selections.at(i);
		for(j = 0; j < med.transitions.size(); j++) {
			new_transition = med.transitions.at(j);
			result.set_transition(new_transition.vertex_from + adder_track, new_transition.vertex_to + adder_track, new_transition.transition_symbol);
		}
		adder_track += med.get_vertex_count();

		result.set_transition(adder_track - 1, vertex_count - 1, '^');
	}

	result.set_final_state(vertex_count - 1);

	return result;
}

Non_Finite_Automata re_to_nfa(string re) {
	stack<char> operators;
	stack<Non_Finite_Automata> operands;
	char op_sym;
	int op_count;
	char cur_sym;
	Non_Finite_Automata *new_sym;
	
	for(string::iterator it = re.begin(); it != re.end(); ++it) {
		cur_sym = *it;
		if(cur_sym != '(' && cur_sym != ')' && cur_sym != '*' && cur_sym != '|' && cur_sym != '.') {
			new_sym = new Non_Finite_Automata();
			new_sym->set_vertex(2);
			new_sym->set_transition(0, 1, cur_sym);
			new_sym->set_final_state(1);
			operands.push(*new_sym);
			delete new_sym;
		} else {
			if(cur_sym == '*') {
				Non_Finite_Automata star_sym = operands.top();
				operands.pop();
				operands.push(STAR(star_sym));
			} else if(cur_sym == '.') {
				operators.push(cur_sym);
			} else if(cur_sym == '|') {
				operators.push(cur_sym);
			} else if(cur_sym == '(') {
				operators.push(cur_sym);
			} else {
				op_count = 0;
				char c;
				op_sym = operators.top();
				if(op_sym == '(') continue;
				do {
					operators.pop();
					op_count++;
				} while(operators.top() != '(');
				operators.pop();
				Non_Finite_Automata op1;
				Non_Finite_Automata op2;
				vector<Non_Finite_Automata> selections;
				if(op_sym == '.') {
					for(int i = 0; i < op_count; i++) {
						op2 = operands.top();
						operands.pop();
						op1 = operands.top();
						operands.pop();
						operands.push(concatinate(op1, op2));
					}
				} else if(op_sym == '|'){
					selections.assign(op_count + 1, Non_Finite_Automata());
					int tracker = op_count;
					for(int i = 0; i < op_count + 1; i++) {
						selections.at(tracker) = operands.top();
						tracker--;
						operands.pop();
					}
					operands.push(or_selection(selections, op_count+1));
				} else {
					
				}
			}
		}
	}

	return operands.top();
}


int main() {
	cout<<"\n\nThe Thompson's Construction Algorithm takes a regular expression as an input "
		<<"and returns its corresponding Non-Deterministic Finite Automaton \n\n";
	cout<<"\n\nThe basic building blocks for constructing the NFA are : \n";

	Non_Finite_Automata a, b;

	cout<<"\nFor the regular expression segment : (a)";
	a.set_vertex(2);
	a.set_transition(0, 1, 'a');
	a.set_final_state(1);
	a.display();
//	getch();

	cout<<"\nFor the regular expression segment : (b)";
	b.set_vertex(2);
	b.set_transition(0, 1, 'b');
	b.set_final_state(1);
	b.display();
//	getch();

	cout<<"\nFor the regular expression segment [Concatenation] : (a.b)";
	Non_Finite_Automata ab = concatinate(a, b);
	ab.display();
//	getch();

	cout<<"\nFor the regular expression segment [STAR Closure] : (a*)";
	Non_Finite_Automata a_star = STAR(a);
	a_star.display();
//	getch();

	cout<<"\nFor the regular expression segment [Or] : (a|b)";
	int no_of_selections;
	no_of_selections = 2;
	vector<Non_Finite_Automata> selections(no_of_selections, Non_Finite_Automata());
	selections.at(0) = a;
	selections.at(1) = b;
	Non_Finite_Automata a_or_b = or_selection(selections, no_of_selections);
	a_or_b.display();	
//	getch();


	string re;
	set<char> symbols;

	cout<<"\n*****\t*****\t*****\n";
	cout<<"\nFORMAT : \n"
		<<"> Explicitly mention concatenation with a '.' operator \n"
		<<"> Enclose every concatenation and or section by parantheses \n"
		<<"> Enclose the entire regular expression with parantheses \n\n";

	cout<<"For example : \nFor the regular expression (a.(b|c))  -- \n";
	Non_Finite_Automata example_nfa = re_to_nfa("(a.(b|c))");
	example_nfa.display();
	
	cout<<"\n\nEnter the regular expression in the above mentioned format - \n\n";
	cin>>re;


	cout<<"\n\nThe required Non_Finite_Automata has the transitions : \n\n";
	
	Non_Finite_Automata required_nfa;
	required_nfa = re_to_nfa(re);
	required_nfa.display();	

	return 0;
}
